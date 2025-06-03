package com.example.todotechproject.excepciones.CajeroExcepciones;


public class OrdenNoEncontradaException extends RuntimeException {
    public OrdenNoEncontradaException(String mensaje) {
        super(mensaje);
    }
}
