package com.example.todotechproject.controlador;

import com.example.todotechproject.dto.OrdenVenta.CrearOrdenDTO;
import com.example.todotechproject.dto.OrdenVenta.OrdenVentaDTO;
import com.example.todotechproject.modelo.entidades.OrdenVenta;
import com.example.todotechproject.modelo.enums.EstadoOrden;
import com.example.todotechproject.servicios.OrdenVentaServicios.OrdenVentaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.todotechproject.utils.Mappers.OrdenVentaMapper.toDTO;

@RestController
@RequestMapping("/api/ordenes")
public class OrdenVentaControlador {

    @Autowired
    private OrdenVentaServicio ordenVentaServicio;

    @PostMapping("/crear")
    public ResponseEntity<OrdenVenta> crearOrden(@RequestBody CrearOrdenDTO request) throws Exception {
        OrdenVenta orden = ordenVentaServicio.crearOrdenVenta(request);
        return ResponseEntity.ok(orden);
    }

    //con el mapper

    @GetMapping("/ultima")
    public ResponseEntity<OrdenVentaDTO> obtenerUltimaOrden() {
        OrdenVentaDTO orden = ordenVentaServicio.obtenerUltimaOrden();
        if (orden != null) {
            return ResponseEntity.ok(orden);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenVentaDTO> obtenerOrdenPorId(@PathVariable Long id) {
        return ordenVentaServicio.obtenerOrdenPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping("/crear-temporal")
    public ResponseEntity<OrdenVentaDTO> crearOrdenTemporal() {
        OrdenVenta orden = ordenVentaServicio.crearOrdenTemporal(); // Retorna una orden vacía
        return ResponseEntity.ok(toDTO(orden));
    }

    @GetMapping("/obtenerTodos")
    public ResponseEntity<List<OrdenVentaDTO>> obtenerTodasLasOrdenes() {
        List<OrdenVentaDTO> ordenes = ordenVentaServicio.obtenerTodasLasOrdenes();
        if (ordenes.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(ordenes);
        }
    }

    @GetMapping("/por-fecha")
    public ResponseEntity<List<OrdenVentaDTO>> obtenerOrdenesPorFecha() {
        return ResponseEntity.ok(ordenVentaServicio.listarOrdenesPorFecha());
    }

    @GetMapping("/por-valor")
    public ResponseEntity<List<OrdenVentaDTO>> obtenerOrdenesPorValor() {
        return ResponseEntity.ok(ordenVentaServicio.listarOrdenesPorValor());
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<OrdenVentaDTO>> obtenerOrdenesPorEstado(@PathVariable EstadoOrden estado) {
        return ResponseEntity.ok(ordenVentaServicio.listarOrdenesPorEstado(estado));
    }



}
