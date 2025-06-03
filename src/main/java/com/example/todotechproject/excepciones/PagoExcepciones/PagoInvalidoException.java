package com.example.todotechproject.excepciones.PagoExcepciones;

public class PagoInvalidoException extends RuntimeException {
    public PagoInvalidoException(String mensaje) {
        super(mensaje);
    }
}
