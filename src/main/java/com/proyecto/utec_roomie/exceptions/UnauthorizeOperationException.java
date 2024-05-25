package com.proyecto.utec_roomie.exceptions;

public class UnauthorizeOperationException extends RuntimeException{
    public UnauthorizeOperationException(String message) {
        super(message);
    }
}
