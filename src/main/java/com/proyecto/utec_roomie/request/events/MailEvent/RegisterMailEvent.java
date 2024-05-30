package com.proyecto.utec_roomie.request.events.MailEvent;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterMailEvent {
    private String nombre;
    private String email;
    private String apellido;
    private String asunto;
    private String role;

    public RegisterMailEvent(String email, String nombre, String apellido, String role) {
        this.nombre = nombre;
        this.email = email;
        this.apellido = apellido;
        this.role = role;
    }
}
