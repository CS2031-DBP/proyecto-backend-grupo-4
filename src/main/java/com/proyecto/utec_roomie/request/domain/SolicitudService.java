package com.proyecto.utec_roomie.request.domain;

import com.proyecto.utec_roomie.auth.utils.AuthorizationUtils;
import com.proyecto.utec_roomie.exceptions.UnauthorizeOperationException;
import com.proyecto.utec_roomie.exceptions.UniqueResourceAlreadyExists;
import com.proyecto.utec_roomie.request.infrastructure.SolicitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolicitudService {

    private final AuthorizationUtils authorizationUtils;
    private final SolicitudRepository solicitudRepository;

    @Autowired
    public SolicitudService(AuthorizationUtils authorizationUtils, SolicitudRepository solicitudRepository) {
        this.authorizationUtils = authorizationUtils;
        this.solicitudRepository = solicitudRepository;
    }

    public void crearSolicitud(Long publicacionId, Solicitud solicitud) {
        String role = authorizationUtils.getCurrentUserRole();
        if (role.equals("ROOMIE")) {
            String usermail = authorizationUtils.getCurrentUserEmail();
            if(solicitudRepository.findByPublicacionIdAndRoomieEmail(publicacionId, usermail).isEmpty()){
                throw new UniqueResourceAlreadyExists("Ya existe solicitud");
            };
        }
        throw new UnauthorizeOperationException("No es roomie");
    }
}
