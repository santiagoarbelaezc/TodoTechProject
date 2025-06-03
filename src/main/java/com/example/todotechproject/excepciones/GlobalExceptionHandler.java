package com.example.todotechproject.excepciones;

import com.example.todotechproject.excepciones.CajeroExcepciones.*;
import com.example.todotechproject.excepciones.DetalleOrdenExcepciones.OperacionInvalidaException;
import com.example.todotechproject.excepciones.DetalleOrdenExcepciones.RecursoNoEncontradoException;
import com.example.todotechproject.excepciones.DetalleOrdenExcepciones.StockInsuficienteException;
import com.example.todotechproject.excepciones.OrdenVentaExcepciones.DescuentoInvalidoException;
import com.example.todotechproject.excepciones.OrdenVentaExcepciones.DescuentoYaAplicadoException;
import com.example.todotechproject.excepciones.PagoExcepciones.MontoPagoInvalidoException;
import com.example.todotechproject.excepciones.PagoExcepciones.OrdenNoExisteException;
import com.example.todotechproject.excepciones.PagoExcepciones.PagoInvalidoException;
import com.example.todotechproject.excepciones.ProductoExcepciones.CategoriaNoEncontradaException;
import com.example.todotechproject.excepciones.UsuarioExcepciones.CredencialesInvalidasException;
import com.example.todotechproject.excepciones.UsuarioExcepciones.UsuarioNoEncontradoException;
import com.example.todotechproject.excepciones.UsuarioExcepciones.UsuarioYaExisteException;
import com.example.todotechproject.excepciones.VendedorExcepciones.CodigoProductoInvalidoException;
import com.example.todotechproject.excepciones.VendedorExcepciones.ProductoNoEncontradoException;
import com.example.todotechproject.excepciones.VendedorExcepciones.TrabajadorNoEncontradoException;
import com.example.todotechproject.excepciones.VendedorExcepciones.UsuarioNoAsociadoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // --- USUARIO ---
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

    // --- VENDEDOR ---
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
        return construirRespuesta(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(CodigoProductoInvalidoException.class)
    public ResponseEntity<Object> manejarCodigoProductoInvalido(CodigoProductoInvalidoException ex) {
        return construirRespuesta(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    // --- CAJERO ---
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

    @ExceptionHandler(MetodoPagoNoSoportadoException.class)
    public ResponseEntity<Object> manejarMetodoPagoNoSoportado(MetodoPagoNoSoportadoException ex) {
        return construirRespuesta(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    // --- PRODUCTO ---
    @ExceptionHandler(CategoriaNoEncontradaException.class)
    public ResponseEntity<Object> manejarCategoriaNoEncontrada(CategoriaNoEncontradaException ex) {
        return construirRespuesta(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    // --- ORDEN DE VENTA ---
    @ExceptionHandler(DescuentoInvalidoException.class)
    public ResponseEntity<Object> manejarDescuentoInvalido(DescuentoInvalidoException ex) {
        return construirRespuesta(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(DescuentoYaAplicadoException.class)
    public ResponseEntity<Object> manejarDescuentoYaAplicado(DescuentoYaAplicadoException ex) {
        return construirRespuesta(HttpStatus.CONFLICT, ex.getMessage());
    }

    // --- DETALLLE ORDEN ---
    @ExceptionHandler(OperacionInvalidaException.class)
    public ResponseEntity<Object> manejarOperacionInvalida(OperacionInvalidaException ex) {
        return construirRespuesta(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<Object> manejarRecursoNoEncontrado(RecursoNoEncontradoException ex) {
        return construirRespuesta(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(StockInsuficienteException.class)
    public ResponseEntity<Object> manejarStockInsuficiente(StockInsuficienteException ex) {
        return construirRespuesta(HttpStatus.CONFLICT, ex.getMessage());
    }

    // --- PAGO ---
    @ExceptionHandler(PagoInvalidoException.class)
    public ResponseEntity<Object> manejarPagoInvalido(PagoInvalidoException ex) {
        return construirRespuesta(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(OrdenNoExisteException.class)
    public ResponseEntity<Object> manejarOrdenNoExiste(OrdenNoExisteException ex) {
        return construirRespuesta(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(MontoPagoInvalidoException.class)
    public ResponseEntity<Object> manejarMontoPagoInvalido(MontoPagoInvalidoException ex) {
        return construirRespuesta(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    // --- EXCEPCIÓN GENERAL ---
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> manejarExcepcionGeneral(Exception ex) {
        return construirRespuesta(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor: " + ex.getMessage());
    }

    // --- MÉTODO AUXILIAR ---
    private ResponseEntity<Object> construirRespuesta(HttpStatus estado, String mensaje) {
        Map<String, Object> cuerpo = new LinkedHashMap<>();
        cuerpo.put("timestamp", LocalDateTime.now());
        cuerpo.put("estado", estado.value());
        cuerpo.put("error", estado.getReasonPhrase());
        cuerpo.put("mensaje", mensaje);
        return new ResponseEntity<>(cuerpo, estado);
    }
}
