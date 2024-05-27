package com.proyecto.utec_roomie.request.eventos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HelloEmailEvent {
    private String nombre;
    private String email;
    private String apellido;
    private String asunto;
    public HelloEmailEvent(String email,String nombre,String apellido) {
        this.nombre = nombre;
        this.email = email;
        this.apellido = apellido;

    }
}
