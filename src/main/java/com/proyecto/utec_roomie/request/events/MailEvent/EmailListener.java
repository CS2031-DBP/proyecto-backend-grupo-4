package com.proyecto.utec_roomie.request.events.MailEvent;
import com.proyecto.utec_roomie.request.eventos.HelloEmailEvent;
import com.proyecto.utec_roomie.request.EmailService;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EmailListener {

    private final EmailService emailService;

    public EmailListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @EventListener
    @Async
    public void handleHelloEmailEvent(HelloEmailEvent event) {
        emailService.sendSimpleMessage(event.getEmail(), "Correo de Prueba",
                event.getNombre(),event.getApellido());
    }
}