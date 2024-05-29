package com.proyecto.utec_roomie.request.dto;


import com.proyecto.utec_roomie.request.domain.SolicitudStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SolicitudResponseDto {

    private String nombre_roomie;
    private String apellido_roomie;

    private String nombre_anfitrion;
    private String apellido_anfitrion;

    private String mensaje;

    private Date fecha_inicio;
    private Date fecha_fin;

    private SolicitudStatus status;

}
