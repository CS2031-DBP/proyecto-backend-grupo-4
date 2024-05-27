package com.proyecto.utec_roomie.request.events.MailEvent;

import com.proyecto.utec_roomie.request.domain.Solicitud;
import com.proyecto.utec_roomie.request.emailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EmailListener {

    @Autowired
    private emailService e_mailService;

    @EventListener
    @Async
    public void handleHelloEmailEvent(com.proyecto.utec_roomie.request.eventos.HelloEmailEvent event) {
        e_mailService.sendSimpleMessage(event.getEmail(), "Correo de Prueba",
                event.getNombre(),event.getApellido());
    }
}