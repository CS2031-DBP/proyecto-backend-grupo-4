package com.proyecto.utec_roomie.Publicacion.application;


import com.proyecto.utec_roomie.Publicacion.domain.Publicacion;
import com.proyecto.utec_roomie.Publicacion.domain.PublicacionService;
import com.proyecto.utec_roomie.Publicacion.dto.PublicacionRequestDto;
import com.proyecto.utec_roomie.Publicacion.dto.PublicacionResponseDto;
import com.proyecto.utec_roomie.Solicitud.domain.Solicitud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/publicaciones")
public class PublicacionController {

    @Autowired
    private PublicacionService publicacionService;

//    @PatchMapping("/{publicacion_id}")
//    public ResponseEntity<Void> addSolicitud(@PathVariable Long publicacion_id, @RequestBody Solicitud solicitud) {
//        publicacionService.anadirSolicitud(publicacion_id,solicitud);
//        return ResponseEntity.ok().build();
//    }

    @PostMapping
    public ResponseEntity<Void> crearPublicacion(@RequestBody PublicacionRequestDto publicacionRequestDto) {
        String uri = publicacionService.crearPublicacion(publicacionRequestDto);
        return ResponseEntity.created(URI.create(uri)).build();
    }

    @DeleteMapping("/{publicacion_id}")
    public ResponseEntity<Void> eliminarPublicacion(@PathVariable Long publicacion_id) {
        publicacionService.eliminarPublicacion(publicacion_id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{publicacion_id}")
    public ResponseEntity<Void> updatePublicacion(@PathVariable Long publicacion_id,
                                                  @RequestBody PublicacionResponseDto publicacionResponseDto){
        publicacionService.updatePublicacion(publicacion_id,publicacionResponseDto);
        return ResponseEntity.ok().build();
    }


}
