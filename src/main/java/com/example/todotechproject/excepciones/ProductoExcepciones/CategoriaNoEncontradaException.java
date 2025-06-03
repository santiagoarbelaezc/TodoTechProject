package com.example.todotechproject.excepciones.ProductoExcepciones;


public class CategoriaNoEncontradaException extends RuntimeException {
    public CategoriaNoEncontradaException(String mensaje) {
        super(mensaje);
    }
}
