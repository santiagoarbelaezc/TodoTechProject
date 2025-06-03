package com.example.todotechproject.excepciones.CajeroExcepciones;

public class OrdenNoPagadaException extends RuntimeException {
    public OrdenNoPagadaException(String mensaje) {
        super(mensaje);
    }
}
