package com.proyecto.utec_roomie.request.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SolicitudRequestDto {

    private String mensaje;
    private Date fecha_inicio;
    private Date fecha_fin;

}
