package com.example.todotechproject.excepciones.CajeroExcepciones;

public class MetodoPagoNoSoportadoException extends RuntimeException {
    public MetodoPagoNoSoportadoException(String mensaje) {
        super(mensaje);
    }
}
