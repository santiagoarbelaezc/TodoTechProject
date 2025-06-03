package com.example.todotechproject.excepciones.CajeroExcepciones;

public class MetodoPagoInvalidoException extends RuntimeException {
    public MetodoPagoInvalidoException(String mensaje) {
        super(mensaje);
    }
}
