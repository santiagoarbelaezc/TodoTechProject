package com.example.todotechproject.excepciones.CajeroExcepciones;

public class ClienteIdInvalidoException extends RuntimeException {
    public ClienteIdInvalidoException(String mensaje) {
        super(mensaje);
    }
}
