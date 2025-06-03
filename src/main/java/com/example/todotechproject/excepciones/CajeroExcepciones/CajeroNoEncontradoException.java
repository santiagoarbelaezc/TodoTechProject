package com.example.todotechproject.excepciones.CajeroExcepciones;

public class CajeroNoEncontradoException extends RuntimeException {
    public CajeroNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
