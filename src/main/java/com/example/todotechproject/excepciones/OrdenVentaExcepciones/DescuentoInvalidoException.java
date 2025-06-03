package com.example.todotechproject.excepciones.OrdenVentaExcepciones;

public class DescuentoInvalidoException extends RuntimeException {
    public DescuentoInvalidoException(String mensaje) {
        super(mensaje);
    }
}
