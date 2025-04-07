package com.example.todotechproject.controlador;

import com.example.todotechproject.dto.OrdenVenta.CrearOrdenDTO;
import com.example.todotechproject.modelo.entidades.OrdenVenta;
import com.example.todotechproject.servicios.OrdenVentaServicios.OrdenVentaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
