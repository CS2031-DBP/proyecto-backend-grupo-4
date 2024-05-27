package com.proyecto.utec_roomie.publication.application;


import com.proyecto.utec_roomie.publication.domain.PublicacionService;
import com.proyecto.utec_roomie.publication.dto.PublicacionRequestDto;
import com.proyecto.utec_roomie.publication.dto.PublicacionResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/publicaciones")
public class PublicacionController {

    private final PublicacionService publicacionService;

    public PublicacionController(PublicacionService publicacionService) {
        this.publicacionService = publicacionService;
    }


    @PostMapping
    public ResponseEntity<Void> crearPublicacion(@RequestBody PublicacionRequestDto publicacionRequestDto) {
        String uri = publicacionService.crearPublicacion(publicacionRequestDto);
        return ResponseEntity.created(URI.create(uri)).build();
    }

    @GetMapping
    public ResponseEntity<List<PublicacionResponseDto>> listarPublicaciones() {
        return ResponseEntity.ok(publicacionService.getPublicaciones());
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminarPublicacion() {
        publicacionService.eliminarPublicacion();
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updatePublicacion(@RequestBody PublicacionResponseDto publicacionResponseDto){
        publicacionService.updatePublicacion(publicacionResponseDto);
        return ResponseEntity.ok().build();
    }


}
