package com.example.todotechproject.excepciones.UsuarioExcepciones;

public class CredencialesInvalidasException extends RuntimeException {
    public CredencialesInvalidasException(String mensaje) {
        super(mensaje);
    }
}
