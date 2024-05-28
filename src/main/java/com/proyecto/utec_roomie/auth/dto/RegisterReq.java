package com.proyecto.utec_roomie.auth.dto;


import lombok.Data;

import java.util.Date;

@Data
public class RegisterReq {
    private String nombre;
    private String apellido;
    private String telefono;
    private String carrera;
    private Date fechaNacimiento;
    private String email;
    private String password;
    private Boolean isAnfitrion=false;
}