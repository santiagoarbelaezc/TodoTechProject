package com.example.todotechproject.excepciones.PagoExcepciones;

public class OrdenNoExisteException extends RuntimeException {
    public OrdenNoExisteException(String mensaje) {
        super(mensaje);
    }
}
