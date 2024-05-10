package com.proyecto.utec_roomie.Publicacion.domain;

import com.proyecto.utec_roomie.Publicacion.application.PublicacionController;
import com.proyecto.utec_roomie.Publicacion.infraestructure.PublicacionRepository;
import com.proyecto.utec_roomie.Solicitud.domain.Solicitud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublicacionService {

    @Autowired
    private PublicacionRepository publicacionRepository;

    public void anadirSolicitud(Long publicacion_id, Solicitud solicitud){
        Optional<Publicacion> p =  publicacionRepository.findById(publicacion_id);
        if(p.isEmpty()){
            //error handilng
        }
        Publicacion publicacion = p.get();
        List<Solicitud> lista_solicitudes = publicacion.getSolicitud();
        lista_solicitudes.add(solicitud);
        publicacion.setSolicitud(lista_solicitudes);
        publicacionRepository.save(publicacion);

    }

}
