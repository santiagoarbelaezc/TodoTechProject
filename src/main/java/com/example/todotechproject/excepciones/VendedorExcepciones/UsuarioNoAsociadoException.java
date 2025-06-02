package com.example.todotechproject.excepciones.VendedorExcepciones;

public class UsuarioNoAsociadoException extends RuntimeException {
    public UsuarioNoAsociadoException(String mensaje) {
        super(mensaje);
    }
}
