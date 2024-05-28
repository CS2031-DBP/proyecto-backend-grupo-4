package com.proyecto.utec_roomie.request.domain;

import com.proyecto.utec_roomie.Arrendamiento.domain.Arrendamiento;
import com.proyecto.utec_roomie.Arrendamiento.infrastructure.ArrendamientoRepository;
import com.proyecto.utec_roomie.auth.utils.AuthorizationUtils;
import com.proyecto.utec_roomie.exceptions.ResourceNotFoundException;
import com.proyecto.utec_roomie.exceptions.UnauthorizeOperationException;
import com.proyecto.utec_roomie.exceptions.UniqueResourceAlreadyExists;
import com.proyecto.utec_roomie.host.domain.Anfitrion;
import com.proyecto.utec_roomie.host.infrastructure.AnfitrionRepository;
import com.proyecto.utec_roomie.publication.domain.Publicacion;
import com.proyecto.utec_roomie.publication.domain.PublicacionStatus;
import com.proyecto.utec_roomie.publication.infraestructure.PublicacionRepository;
import com.proyecto.utec_roomie.request.EmailService;
import com.proyecto.utec_roomie.request.dto.SolicitudResponseDto;
import com.proyecto.utec_roomie.request.infrastructure.SolicitudRepository;
import com.proyecto.utec_roomie.roomie.domain.Roomie;
import com.proyecto.utec_roomie.roomie.infrastructure.RoomieRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SolicitudService {

    private final AuthorizationUtils authorizationUtils;
    private final SolicitudRepository solicitudRepository;
    private final RoomieRepository roomieRepository;
    private final PublicacionRepository publicacionRepository;
    private final AnfitrionRepository anfitrionRepository;
    private final ArrendamientoRepository arrendamientoRepository;
    private final EmailService emailService;
    private final ModelMapper modelMapper;

    @Autowired
    public SolicitudService(AuthorizationUtils authorizationUtils, SolicitudRepository solicitudRepository
                            , RoomieRepository roomieRepository, PublicacionRepository publicacionRepository,
                            AnfitrionRepository anfitrionRepository, ArrendamientoRepository arrendamientoRepository,
                            EmailService emailService, ModelMapper modelMapper) {
        this.authorizationUtils = authorizationUtils;
        this.solicitudRepository = solicitudRepository;
        this.roomieRepository = roomieRepository;
        this.publicacionRepository = publicacionRepository;
        this.anfitrionRepository = anfitrionRepository;
        this.arrendamientoRepository = arrendamientoRepository;
        this.emailService = emailService;
        this.modelMapper = modelMapper;
    }

    public void crearSolicitud(Long publicacionId) {
        if(publicacionRepository.findById(publicacionId).isEmpty()) {
            throw new ResourceNotFoundException("No existe esa publicacion");
        }
        String usermail = authorizationUtils.getCurrentUserEmail();
        if(solicitudRepository.findByPublicacionIdAndRoomieEmail(publicacionId, usermail).isPresent()){
            throw new UniqueResourceAlreadyExists("Ya existe solicitud");
        }
        Roomie roomie = roomieRepository.findByEmail(usermail).get();
        Solicitud solicitud = new Solicitud();
        solicitud.setRoomie(roomie);
        solicitud.setPublicacion(publicacionRepository.findById(publicacionId).get());
        solicitudRepository.save(solicitud);
        emailService.sendSimpleMessage(usermail,"Nueva solicitud creada!", roomie.getNombre(),roomie.getApellido());
    }


    public List<SolicitudResponseDto> getSolicitudes() {
        List<SolicitudResponseDto> solicitudResponseDtoList = new ArrayList<>();
        String role = authorizationUtils.getCurrentUserRole();
        String usermail = authorizationUtils.getCurrentUserEmail();
        if(role.equals("ANFITRION")) {
            Anfitrion anfitrion = anfitrionRepository.findByEmail(usermail).get();
            Long publicacion_id = publicacionRepository.findByAnfitrionEmail(usermail).get().getId();
            List<Solicitud> solicitudList = solicitudRepository.findAllByPublicacionId(publicacion_id);
            for (Solicitud solicitud : solicitudList) {
                SolicitudResponseDto solicitudResponseDto = new SolicitudResponseDto();
                solicitudResponseDto.setNombre_anfitrion(anfitrion.getNombre());
                solicitudResponseDto.setApellido_roomie(anfitrion.getApellido());
                solicitudResponseDto.setNombre_roomie(solicitud.getRoomie().getNombre());
                solicitudResponseDto.setApellido_roomie(solicitud.getRoomie().getApellido());
                solicitudResponseDtoList.add(solicitudResponseDto);
            }
        }
        else if(role.equals("ROOMIE")) {
            List<Solicitud> solicitudList = solicitudRepository.findAllByRoomieId(usermail);
            for (Solicitud solicitud : solicitudList) {
                Anfitrion anfitrion = solicitud.getPublicacion().getAnfitrion();
                SolicitudResponseDto solicitudResponseDto = new SolicitudResponseDto();
                solicitudResponseDto.setNombre_anfitrion(anfitrion.getNombre());
                solicitudResponseDto.setApellido_roomie(anfitrion.getApellido());
                solicitudResponseDto.setNombre_roomie(solicitud.getRoomie().getNombre());
                solicitudResponseDto.setApellido_roomie(solicitud.getRoomie().getApellido());
                solicitudResponseDtoList.add(solicitudResponseDto);
            }
        }
        return solicitudResponseDtoList;
    }

    public Arrendamiento aceptarSolicitud(Long solicitudId, Date fecha_inicio, Date fecha_fin) {
        String role = authorizationUtils.getCurrentUserRole();
        if(!role.equals("ANFITRION")) {
            throw new UnauthorizeOperationException("No es anfitrion");
        }
        String usermail = authorizationUtils.getCurrentUserEmail();
        Optional<Solicitud> s = solicitudRepository.findById(solicitudId);
        if (s.isEmpty()){
            throw new ResourceNotFoundException("No existe solicitud");
        }
        Publicacion publicacion = s.get().getPublicacion();
        Solicitud solicitud = s.get();
        solicitud.setSolicitudStatus(SolicitudStatus.ACCEPTED);
        publicacion.setPublicacionStatus(PublicacionStatus.TAKEN);
        publicacionRepository.save(publicacion);
        solicitudRepository.setAllSolicitudCancelledByPublicacionId(publicacion.getId());
        solicitudRepository.save(solicitud);

        Arrendamiento arrendamiento = new Arrendamiento();
        arrendamiento.setAnfitrion(anfitrionRepository.findByEmail(usermail).get());
        arrendamiento.setRoomie(solicitud.getRoomie());
        arrendamiento.setFechaInicio(fecha_inicio);
        arrendamiento.setFechaFin(fecha_fin);


        return arrendamientoRepository.save(arrendamiento);
    }

    public void eliminarSolicitud(Long publicacionId) {
        String usermail = authorizationUtils.getCurrentUserEmail();
        Optional<Solicitud> s = solicitudRepository.findByPublicacionIdAndRoomieEmail(publicacionId, usermail);
        if (s.isEmpty()) {
            throw new UniqueResourceAlreadyExists("No existe solicitud");
        }
        solicitudRepository.delete(s.get());
    }

}


