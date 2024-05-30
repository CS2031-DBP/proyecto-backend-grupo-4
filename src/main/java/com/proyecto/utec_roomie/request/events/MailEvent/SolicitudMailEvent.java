package com.proyecto.utec_roomie.request.events.MailEvent;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SolicitudMailEvent {
    private String nombre_roomie;
    private String apellido_roomie;
    private String fecha_inicio;
    private String fecha_fin;
}
