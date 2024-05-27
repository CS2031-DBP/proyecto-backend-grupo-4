package com.proyecto.utec_roomie.publication.domain;

import lombok.Getter;

@Getter
public enum PublicacionStatus {
    AVAILABLE("A"),TAKEN("T");

    private String code;
    private PublicacionStatus(String code) {
        this.code = code;
    }
}
