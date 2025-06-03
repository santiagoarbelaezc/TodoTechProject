package com.example.todotechproject.excepciones.CajeroExcepciones;

public class OrdenYaPagadaException extends RuntimeException {
    public OrdenYaPagadaException(String mensaje) {
        super(mensaje);
    }
}
