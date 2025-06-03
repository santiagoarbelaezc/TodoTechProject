package com.example.todotechproject.excepciones.DetalleOrdenExcepciones;

public class StockInsuficienteException extends RuntimeException {
    public StockInsuficienteException(String mensaje) {
        super(mensaje);
    }
}
