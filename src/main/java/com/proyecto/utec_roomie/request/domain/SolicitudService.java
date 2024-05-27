package com.proyecto.utec_roomie.request.domain;

import com.proyecto.utec_roomie.auth.utils.AuthorizationUtils;
import com.proyecto.utec_roomie.exceptions.ResourceNotFoundException;
import com.proyecto.utec_roomie.exceptions.UnauthorizeOperationException;
import com.proyecto.utec_roomie.exceptions.UniqueResourceAlreadyExists;
import com.proyecto.utec_roomie.publication.infraestructure.PublicacionRepository;
import com.proyecto.utec_roomie.request.infrastructure.SolicitudRepository;
import com.proyecto.utec_roomie.roomie.infrastructure.RoomieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolicitudService {

    private final AuthorizationUtils authorizationUtils;
    private final SolicitudRepository solicitudRepository;
    private final RoomieRepository roomieRepository;
    private final PublicacionRepository publicacionRepository;

    @Autowired
    public SolicitudService(AuthorizationUtils authorizationUtils, SolicitudRepository solicitudRepository
                            , RoomieRepository roomieRepository, PublicacionRepository publicacionRepository) {
        this.authorizationUtils = authorizationUtils;
        this.solicitudRepository = solicitudRepository;
        this.roomieRepository = roomieRepository;
        this.publicacionRepository = publicacionRepository;
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






}


