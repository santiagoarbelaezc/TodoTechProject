package com.example.todotechproject.excepciones.OrdenVentaExcepciones;

public class DescuentoYaAplicadoException extends RuntimeException {
    public DescuentoYaAplicadoException(String mensaje) {
        super(mensaje);
    }
}
