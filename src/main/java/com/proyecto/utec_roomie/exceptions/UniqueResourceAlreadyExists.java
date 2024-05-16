package com.proyecto.utec_roomie.exceptions;

public class UniqueResourceAlreadyExists extends RuntimeException {
    public UniqueResourceAlreadyExists(String message) {
        super(message);
    }

}