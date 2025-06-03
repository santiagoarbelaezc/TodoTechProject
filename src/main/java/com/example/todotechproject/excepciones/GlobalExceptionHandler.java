package com.example.todotechproject.excepciones;

import com.example.todotechproject.excepciones.CajeroExcepciones.*;
import com.example.todotechproject.excepciones.ProductoExcepciones.CategoriaNoEncontradaException;
import com.example.todotechproject.excepciones.UsuarioExcepciones.CredencialesInvalidasException;
import com.example.todotechproject.excepciones.UsuarioExcepciones.UsuarioYaExisteException;
import com.example.todotechproject.excepciones.VendedorExcepciones.CodigoProductoInvalidoException;
import com.example.todotechproject.excepciones.VendedorExcepciones.ProductoNoEncontradoException;
import com.example.todotechproject.excepciones.VendedorExcepciones.TrabajadorNoEncontradoException;
import com.example.todotechproject.excepciones.VendedorExcepciones.UsuarioNoAsociadoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // EXISTENTES

    @ExceptionHandler(UsuarioNoEncontradoException.class)
    public ResponseEntity<Object> manejarUsuarioNoEncontrado(UsuarioNoEncontradoException ex) {
        return construirRespuesta(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(UsuarioYaExisteException.class)
    public ResponseEntity<Object> manejarUsuarioYaExiste(UsuarioYaExisteException ex) {
        return construirRespuesta(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(CredencialesInvalidasException.class)
    public ResponseEntity<Object> manejarCredencialesInvalidas(CredencialesInvalidasException ex) {
        return construirRespuesta(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }

    // NUEVOS PARA VENDEDOR

    @ExceptionHandler(UsuarioNoAsociadoException.class)
    public ResponseEntity<Object> manejarUsuarioNoAsociado(UsuarioNoAsociadoException ex) {
        return construirRespuesta(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(TrabajadorNoEncontradoException.class)
    public ResponseEntity<Object> manejarTrabajadorNoEncontrado(TrabajadorNoEncontradoException ex) {
        return construirRespuesta(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(ProductoNoEncontradoException.class)
    public ResponseEntity<Object> manejarProductoNoEncontrado(ProductoNoEncontradoException ex) {
        return construirRespuesta(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(CodigoProductoInvalidoException.class)
    public ResponseEntity<Object> manejarCodigoProductoInvalido(CodigoProductoInvalidoException ex) {
        return construirRespuesta(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    // GENERAL

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> manejarExcepcionGeneral(Exception ex) {
        return construirRespuesta(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor: " + ex.getMessage());
    }

    @ExceptionHandler(CajeroNoEncontradoException.class)
    public ResponseEntity<Object> manejarCajeroNoEncontrado(CajeroNoEncontradoException ex) {
        return construirRespuesta(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(OrdenNoEncontradaException.class)
    public ResponseEntity<Object> manejarOrdenNoEncontrada(OrdenNoEncontradaException ex) {
        return construirRespuesta(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(OrdenYaPagadaException.class)
    public ResponseEntity<Object> manejarOrdenYaPagada(OrdenYaPagadaException ex) {
        return construirRespuesta(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(MetodoPagoNoSoportadoException.class)
    public ResponseEntity<Object> manejarMetodoPagoNoSoportado(MetodoPagoNoSoportadoException ex) {
        return construirRespuesta(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(OrdenNoPagadaException.class)
    public ResponseEntity<Object> manejarOrdenNoPagada(OrdenNoPagadaException ex) {
        return construirRespuesta(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(EstadoOrdenInvalidoException.class)
    public ResponseEntity<Object> manejarEstadoOrdenInvalido(EstadoOrdenInvalidoException ex) {
        return construirRespuesta(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(ClienteIdInvalidoException.class)
    public ResponseEntity<Object> manejarClienteIdInvalido(ClienteIdInvalidoException ex) {
        return construirRespuesta(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(MetodoPagoInvalidoException.class)
    public ResponseEntity<Object> manejarMetodoPagoInvalido(MetodoPagoInvalidoException ex) {
        return construirRespuesta(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(CategoriaNoEncontradaException.class)
    public ResponseEntity<Object> manejarCategoriaNoEncontrada(CategoriaNoEncontradaException ex) {
        return construirRespuesta(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(ProductoNoEncontradoException.class)
    public ResponseEntity<Object> manejarProductoNoEncontradoException(ProductoNoEncontradoException ex) {
        return construirRespuesta(HttpStatus.BAD_REQUEST, ex.getMessage());
    }


    private ResponseEntity<Object> construirRespuesta(HttpStatus estado, String mensaje) {
        Map<String, Object> cuerpo = new LinkedHashMap<>();
        cuerpo.put("timestamp", LocalDateTime.now());
        cuerpo.put("estado", estado.value());
        cuerpo.put("error", estado.getReasonPhrase());
        cuerpo.put("mensaje", mensaje);
        return new ResponseEntity<>(cuerpo, estado);
    }







}
