package com.example.todotechproject.excepciones.CajeroExcepciones;

public class EstadoOrdenInvalidoException extends RuntimeException {
    public EstadoOrdenInvalidoException(String mensaje) {
        super(mensaje);
    }
}