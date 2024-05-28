package com.proyecto.utec_roomie.publication.application;


import com.proyecto.utec_roomie.publication.domain.PublicacionService;
import com.proyecto.utec_roomie.publication.dto.PublicacionRequestDto;
import com.proyecto.utec_roomie.publication.dto.PublicacionResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('ROLE_ANFITRION')")
    @PostMapping
    public ResponseEntity<Void> crearPublicacion(@RequestBody PublicacionRequestDto publicacionRequestDto) {
        String uri = publicacionService.crearPublicacion(publicacionRequestDto);
        return ResponseEntity.created(URI.create(uri)).build();
    }

//    @PreAuthorize("hasRole('ROLE_ROOMIE')")
//    @GetMapping("/all")
//    public ResponseEntity<List<PublicacionResponseDto>> listarPublicaciones() {
//        return ResponseEntity.ok(publicacionService.getPublicaciones());
//    }

    @PreAuthorize("hasRole('ROLE_ANFITRION') or hasRole('ROLE_ADMIN')")
    @DeleteMapping
    public ResponseEntity<Void> eliminarPublicacion() {
        publicacionService.eliminarPublicacion();
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_ANFITRION')")
    @PutMapping
    public ResponseEntity<Void> updatePublicacion(@RequestBody PublicacionRequestDto publicacionRequestDto){
        publicacionService.updatePublicacion(publicacionRequestDto);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_ROOMIE')")
    @GetMapping
    public ResponseEntity<List<PublicacionResponseDto>> listarPublicaciones
            (@RequestParam(required = false) Integer bano,
             @RequestParam (required = false) Integer habitacion,
             @RequestParam (required = false) Double max_costo){

        return ResponseEntity.ok(publicacionService.getPublicacionesByQuery(bano, habitacion, max_costo));
    }





}
