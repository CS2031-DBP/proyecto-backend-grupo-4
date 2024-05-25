package com.proyecto.utec_roomie.request.application;

import com.proyecto.utec_roomie.request.domain.Solicitud;
import com.proyecto.utec_roomie.request.domain.SolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/solicitudes")
@RestController
public class SolicitudController
{
    @Autowired
    private SolicitudService solicitudService;

    @PostMapping()
    public ResponseEntity<Void> crearSolicitud(@RequestBody Solicitud solicitud){
        return ResponseEntity.ok().build();
    }

}
