package com.example.todotechproject.excepciones.VendedorExcepciones;

public class CodigoProductoInvalidoException extends RuntimeException {
    public CodigoProductoInvalidoException(String mensaje) {
        super(mensaje);
    }
}