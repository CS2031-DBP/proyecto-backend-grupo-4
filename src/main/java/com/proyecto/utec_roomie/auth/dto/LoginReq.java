package com.proyecto.utec_roomie.auth.dto;

import lombok.Data;

@Data
public class LoginReq {
    private String email;
    private String password;
}