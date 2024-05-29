package com.proyecto.utec_roomie.Arrendamiento.domain;

import com.proyecto.utec_roomie.Arrendamiento.dto.ArrendamientoResponseDto;
import com.proyecto.utec_roomie.Arrendamiento.infrastructure.ArrendamientoRepository;
import com.proyecto.utec_roomie.auth.utils.AuthorizationUtils;
import com.proyecto.utec_roomie.exceptions.ResourceNotFoundException;
import com.proyecto.utec_roomie.request.infrastructure.SolicitudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArrendamientoService {
    private final ArrendamientoRepository arrendamientoRepository;
    private final SolicitudRepository solicitudRepository;
    private final AuthorizationUtils authorizationUtils;

    public ArrendamientoService(ArrendamientoRepository arrendamientoRepository, SolicitudRepository solicitudRepository, AuthorizationUtils authorizationUtils) {
        this.arrendamientoRepository = arrendamientoRepository;
        this.solicitudRepository = solicitudRepository;
        this.authorizationUtils = authorizationUtils;
    }


    public ArrendamientoResponseDto getArrendamiento() {
        String usermail = authorizationUtils.getCurrentUserEmail();
        ArrendamientoResponseDto dto = new ArrendamientoResponseDto();
        Optional<Arrendamiento> arrendamiento = arrendamientoRepository.findByEmail(usermail);
        if (arrendamiento.isEmpty()) {
            throw new ResourceNotFoundException("No tiene ningun arrendamiento");
        }
        dto.setFecha_fin(arrendamiento.get().getFechaFin());
        dto.setApellido_roomie(arrendamiento.get().getRoomie().getApellido());
        dto.setNombre_roomie(arrendamiento.get().getRoomie().getNombre());
        dto.setFecha_inicio(arrendamiento.get().getFechaInicio());
        dto.setApellido_anfitrion(arrendamiento.get().getAnfitrion().getApellido());
        dto.setNombre_anfitrion(arrendamiento.get().getAnfitrion().getNombre());
        return dto;
    }
}
