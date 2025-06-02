package com.example.todotechproject.excepciones.UsuarioExcepciones;

public class UsuarioYaExisteException extends RuntimeException {
    public UsuarioYaExisteException(String mensaje) {
        super(mensaje);
    }
}
