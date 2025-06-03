package com.example.todotechproject.excepciones.PagoExcepciones;

public class MontoPagoInvalidoException extends RuntimeException {
    public MontoPagoInvalidoException(String mensaje) {
        super(mensaje);
    }
}
