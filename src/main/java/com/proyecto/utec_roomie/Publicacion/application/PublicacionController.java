package com.proyecto.utec_roomie.Publicacion.application;


import com.proyecto.utec_roomie.Publicacion.domain.PublicacionService;
import com.proyecto.utec_roomie.Solicitud.domain.Solicitud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publicaciones")
public class PublicacionController {

    @Autowired
    private PublicacionService publicacionService;

    @PatchMapping("/{publicacion_id}")
    public ResponseEntity<Void> addSolicitud(@PathVariable Long publicacion_id, @RequestBody Solicitud solicitud) {
        publicacionService.anadirSolicitud(publicacion_id,solicitud);
        return ResponseEntity.ok().build();
    }

}
