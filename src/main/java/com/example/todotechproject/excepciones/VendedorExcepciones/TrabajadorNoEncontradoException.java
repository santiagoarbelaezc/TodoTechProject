package com.example.todotechproject.excepciones.VendedorExcepciones;

public class TrabajadorNoEncontradoException extends RuntimeException {
    public TrabajadorNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
