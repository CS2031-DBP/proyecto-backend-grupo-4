package com.proyecto.utec_roomie.request.application;

import com.proyecto.utec_roomie.request.domain.Solicitud;
import com.proyecto.utec_roomie.request.domain.SolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/{publicacion_id}")
    public ResponseEntity<String> crearSolicitud(@PathVariable Long publicacion_id){
        solicitudService.crearSolicitud(publicacion_id);
        return ResponseEntity.ok("Solicitud enviada!");
    }

    @GetMapping()
    public ResponseEntity<List<Solicitud>> getSolicitudes(){
        return ResponseEntity.ok(solicitudService.getSolicitudes());
    }


}
