package com.example.todotechproject.excepciones.ProductoExcepciones;

public class ProductoNoEncontradoException extends RuntimeException {
    public ProductoNoEncontradoException(String mensaje) {
        super(mensaje);

    }
}