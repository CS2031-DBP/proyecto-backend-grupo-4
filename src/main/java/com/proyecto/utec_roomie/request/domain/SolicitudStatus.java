package com.proyecto.utec_roomie.request.domain;

import lombok.Getter;

@Getter
public enum SolicitudStatus {
    PENDING("P"),ACCEPTED("A"),CANCELLED("C");

    private String code;

    private SolicitudStatus(String code) {
        this.code = code;
    }
}
