package com.proyecto.utec_roomie.Solicitud.application;

import com.proyecto.utec_roomie.Solicitud.domain.Solicitud;
import com.proyecto.utec_roomie.Solicitud.domain.SolicitudService;
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
        
    }

}
