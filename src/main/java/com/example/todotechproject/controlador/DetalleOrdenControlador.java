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
    @Autowired
    private OrdenVentaServicio ordenVentaServicio;
    @Autowired
    private OrdenVentaRepo ordenVentaRepo;
    @Autowired
    private DetalleOrdenRepo detalleOrdenRepo;

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

    @PostMapping("/aplicar-descuento")
    public ResponseEntity<?> aplicarDescuento(@RequestBody AplicarDescuentoRequest request) {
        try {
            OrdenVentaDescuentoRequest ordenConDescuento = ordenVentaServicio.aplicarDescuento(
                    request.ordenVentaId(),
                    request.porcentajeDescuento()
            );

            return ResponseEntity.ok(ordenConDescuento);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al aplicar descuento: " + e.getMessage()));
        }
    }


    @PostMapping("/remover-descuento")
    public ResponseEntity<?> removerDescuento(@RequestBody Long ordenVentaId) {
        try {
            OrdenVentaDTO ordenDTO = ordenVentaServicio.removerDescuento(ordenVentaId);
            return ResponseEntity.ok(ordenDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al remover descuento: " + e.getMessage()));
        }
    }

}
