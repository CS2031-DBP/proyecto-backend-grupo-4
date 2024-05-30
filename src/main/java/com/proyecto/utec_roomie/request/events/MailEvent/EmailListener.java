package com.proyecto.utec_roomie.request.events.MailEvent;
import com.proyecto.utec_roomie.request.events.MailEvent.RegisterMailEvent;
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
    public void handleRegisterEmailEvent(RegisterMailEvent event) {
        emailService.sendRegisterMessage(event.getEmail(), "Correo de Prueba",
                event.getNombre(),event.getApellido(), event.getRole());
    }

    @EventListener
    @Async
    public void handleSolicitudEmailEvent(RegisterMailEvent event) {
        emailService.sendRegisterMessage(event.getEmail(), "Correo de Prueba",
                event.getNombre(),event.getApellido(), event.getRole());
    }

    @EventListener
    @Async
    public void handleArrendamientoAnfitrionEmailEvent(RegisterMailEvent event) {
        emailService.sendRegisterMessage(event.getEmail(), "Correo de Prueba",
                event.getNombre(),event.getApellido(), event.getRole());
    }

    @EventListener
    @Async
    public void handleArrendamientoRoomieEmailEvent(RegisterMailEvent event) {
        emailService.sendRegisterMessage(event.getEmail(), "Correo de Prueba",
                event.getNombre(),event.getApellido(), event.getRole());
    }
}