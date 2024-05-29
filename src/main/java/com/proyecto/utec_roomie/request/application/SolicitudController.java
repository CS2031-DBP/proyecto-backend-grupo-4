package com.proyecto.utec_roomie.request.application;

import com.proyecto.utec_roomie.Arrendamiento.domain.Arrendamiento;
import com.proyecto.utec_roomie.request.dto.SolicitudRequestDto;
import com.proyecto.utec_roomie.request.dto.SolicitudResponseDto;
import com.proyecto.utec_roomie.request.domain.SolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequestMapping("/solicitudes")
@RestController
public class SolicitudController
{
    private final SolicitudService solicitudService;

    @Autowired
    public SolicitudController(SolicitudService solicitudService){
        this.solicitudService = solicitudService;
    }

    @PreAuthorize("hasRole('ROLE_ROOMIE')")
    @PostMapping("/crear/{publicacion_id}")
    public ResponseEntity<String> crearSolicitud(@PathVariable Long publicacion_id, @RequestBody(required = false)SolicitudRequestDto mensaje){
        solicitudService.crearSolicitud(publicacion_id,mensaje);
        return ResponseEntity.ok("Solicitud enviada!");
    }

    @PreAuthorize("hasRole('ROLE_ANFITRION') or hasRole('ROLE_ROOMIE')")
    @GetMapping
    public ResponseEntity<List<SolicitudResponseDto>> getSolicitudes(){
        return ResponseEntity.ok(solicitudService.getSolicitudes());
    }

    @PreAuthorize("hasRole('ROLE_ANFITRION')")
    @PostMapping("/aceptar/{solicitud_id}")
    public ResponseEntity<Arrendamiento> aceptarSolicitud(@PathVariable Long solicitud_id){
        return ResponseEntity.ok(solicitudService.aceptarSolicitud(solicitud_id));
    }

    @PreAuthorize("hasRole('ROLE_ROOMIE')")
    @DeleteMapping("/eliminar/{publicacion_id}")
    public ResponseEntity<Void> eliminarSolicitud(@PathVariable Long publicacion_id){
        solicitudService.eliminarSolicitud(publicacion_id);
        return ResponseEntity.ok().build();
    }

}
