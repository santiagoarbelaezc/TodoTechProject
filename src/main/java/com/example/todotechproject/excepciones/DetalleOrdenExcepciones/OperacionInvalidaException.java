package com.example.todotechproject.excepciones.DetalleOrdenExcepciones;

public class OperacionInvalidaException extends RuntimeException {
    public OperacionInvalidaException(String mensaje) {
        super(mensaje);
    }
}
