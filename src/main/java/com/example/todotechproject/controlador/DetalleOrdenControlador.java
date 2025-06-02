package com.example.todotechproject.controlador;

import com.example.todotechproject.dto.DetalleOrden.*;
import com.example.todotechproject.dto.OrdenVenta.OrdenVentaDTO;
import com.example.todotechproject.dto.OrdenVenta.OrdenVentaDescuentoRequest;
import com.example.todotechproject.modelo.entidades.DetalleOrden;
import com.example.todotechproject.modelo.entidades.OrdenVenta;
import com.example.todotechproject.repositorios.DetalleOrdenRepo;
import com.example.todotechproject.repositorios.OrdenVentaRepo;
import com.example.todotechproject.servicios.DetalleOrdenServicios.DetalleOrdenServicioImp;
import com.example.todotechproject.servicios.OrdenVentaServicios.OrdenVentaServicio;
import com.example.todotechproject.utils.Mappers.OrdenVentaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/detalles")
public class DetalleOrdenControlador {

    @Autowired
    private DetalleOrdenServicioImp detalleOrdenServicio;

    @PostMapping("/crear")
    public ResponseEntity<DetalleOrdenDTO> crearDetalle(@RequestBody CrearDetalleRequest request) {
        return detalleOrdenServicio.crear(request);
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<String> eliminarDetalle(@RequestBody EliminarDetalleRequest request) {
        return detalleOrdenServicio.eliminar(request);
    }

    @GetMapping("/orden/{ordenId}")
    public ResponseEntity<List<DetalleOrdenDTO>> obtenerDetallesPorOrden(@PathVariable Long ordenId) {
        return detalleOrdenServicio.obtenerDetallesPorOrden(ordenId);
    }

    @PutMapping("/aumentar-cantidad")
    public ResponseEntity<DetalleOrdenDTO> aumentarCantidad(@RequestBody ActualizarCantidadRequest request) {
        DetalleOrdenDTO detalleActualizado = detalleOrdenServicio.aumentarCantidad(
                request.productoId(), request.ordenVentaId()
        );
        return ResponseEntity.ok(detalleActualizado);
    }

    @PutMapping("/disminuir-cantidad")
    public ResponseEntity<DetalleOrdenDTO> disminuirCantidad(@RequestBody ActualizarCantidadRequest request) {
        DetalleOrdenDTO detalleActualizado = detalleOrdenServicio.disminuirCantidad(
                request.productoId(), request.ordenVentaId()
        );
        return ResponseEntity.ok(detalleActualizado);
    }


    @PostMapping("/aplicar-descuento")
    public ResponseEntity<?> aplicarDescuento(@RequestBody AplicarDescuentoRequest request) {
        return detalleOrdenServicio.aplicarDescuentoOrden(request);
    }

    @PostMapping("/remover-descuento")
    public ResponseEntity<?> removerDescuento(@RequestBody Long ordenVentaId) {
        return detalleOrdenServicio.removerDescuentoOrden(ordenVentaId);
    }
}


