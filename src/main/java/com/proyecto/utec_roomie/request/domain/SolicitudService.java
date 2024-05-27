package com.proyecto.utec_roomie.request.domain;

import com.proyecto.utec_roomie.Arrendamiento.domain.Arrendamiento;
import com.proyecto.utec_roomie.Arrendamiento.infrastructure.ArrendamientoRepository;
import com.proyecto.utec_roomie.auth.utils.AuthorizationUtils;
import com.proyecto.utec_roomie.exceptions.ResourceNotFoundException;
import com.proyecto.utec_roomie.exceptions.UnauthorizeOperationException;
import com.proyecto.utec_roomie.exceptions.UniqueResourceAlreadyExists;
import com.proyecto.utec_roomie.host.infrastructure.AnfitrionRepository;
import com.proyecto.utec_roomie.publication.domain.Publicacion;
import com.proyecto.utec_roomie.publication.domain.PublicacionStatus;
import com.proyecto.utec_roomie.publication.infraestructure.PublicacionRepository;
import com.proyecto.utec_roomie.request.infrastructure.SolicitudRepository;
import com.proyecto.utec_roomie.roomie.infrastructure.RoomieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    public SolicitudService(AuthorizationUtils authorizationUtils, SolicitudRepository solicitudRepository
                            , RoomieRepository roomieRepository, PublicacionRepository publicacionRepository, AnfitrionRepository anfitrionRepository, ArrendamientoRepository arrendamientoRepository) {
        this.authorizationUtils = authorizationUtils;
        this.solicitudRepository = solicitudRepository;
        this.roomieRepository = roomieRepository;
        this.publicacionRepository = publicacionRepository;
        this.anfitrionRepository = anfitrionRepository;
        this.arrendamientoRepository = arrendamientoRepository;
    }

    public void crearSolicitud(Long publicacionId) {
        String role = authorizationUtils.getCurrentUserRole();
        if(publicacionRepository.findById(publicacionId).isEmpty()) {
            throw new ResourceNotFoundException("No existe esa publicacion");
        }
        if (!role.equals("ROOMIE")) {
        throw new UnauthorizeOperationException("No es roomie");
        }
        String usermail = authorizationUtils.getCurrentUserEmail();
        if(solicitudRepository.findByPublicacionIdAndRoomieEmail(publicacionId, usermail).isPresent()){
            throw new UniqueResourceAlreadyExists("Ya existe solicitud");
        }
        Solicitud solicitud = new Solicitud();
        solicitud.setRoomie(roomieRepository.findByEmail(usermail).get());
        solicitud.setPublicacion(publicacionRepository.findById(publicacionId).get());
        solicitudRepository.save(solicitud);
    }


    public List<Solicitud> getSolicitudes() {
        String role = authorizationUtils.getCurrentUserRole();
        String usermail = authorizationUtils.getCurrentUserEmail();
        if(role.equals("ANFITRION")) {
            Long publicacion_id = publicacionRepository.findByAnfitrionEmail(usermail).get().getId();
            return solicitudRepository.findAllByPublicacionId(publicacion_id);
        }
        if(role.equals("ROOMIE")) {
            return solicitudRepository.findByRoomieId(usermail);
        }
        return solicitudRepository.findAll();
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
}


