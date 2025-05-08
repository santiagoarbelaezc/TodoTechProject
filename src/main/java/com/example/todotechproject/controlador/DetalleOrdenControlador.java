package com.example.todotechproject.controlador;

import com.example.todotechproject.dto.DetalleOrden.ActualizarCantidadRequest;
import com.example.todotechproject.dto.DetalleOrden.CrearDetalleRequest;
import com.example.todotechproject.dto.DetalleOrden.DetalleOrdenDTO;
import com.example.todotechproject.dto.DetalleOrden.EliminarDetalleRequest;
import com.example.todotechproject.servicios.DetalleOrdenServicios.DetalleOrdenServicioImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return ResponseEntity.ok(detalleOrdenServicio.obtenerPorOrdenVenta(ordenId));
    }

    @PutMapping("/aumentar-cantidad")
    public ResponseEntity<DetalleOrdenDTO> aumentarCantidad(@RequestBody ActualizarCantidadRequest request) {
        return detalleOrdenServicio.aumentarCantidad(request.productoId(), request.ordenVentaId());
    }

    @PutMapping("/disminuir-cantidad")
    public ResponseEntity<DetalleOrdenDTO> disminuirCantidad(@RequestBody ActualizarCantidadRequest request) {
        return detalleOrdenServicio.disminuirCantidad(request.productoId(), request.ordenVentaId());
    }



}
