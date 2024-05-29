package com.proyecto.utec_roomie.Arrendamiento.application;

import com.proyecto.utec_roomie.Arrendamiento.domain.ArrendamientoService;
import com.proyecto.utec_roomie.Arrendamiento.dto.ArrendamientoResponseDto;
import com.proyecto.utec_roomie.Arrendamiento.infrastructure.ArrendamientoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/arredamiento")
public class ArrendamientoController {
    private final ArrendamientoService arrendamientoService;

    public ArrendamientoController(ArrendamientoService arrendamientoService) {
        this.arrendamientoService = arrendamientoService;
    }

    @PreAuthorize("hasRole('ROLE_ROOMIE') or hasRole('ROLE_ANFITRION')")
    @GetMapping
    public ResponseEntity<ArrendamientoResponseDto> getArrendamiento() {
        return ResponseEntity.ok(arrendamientoService.getArrendamiento());
    }

}
