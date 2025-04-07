package com.example.todotechproject.controlador;

import com.example.todotechproject.dto.DetalleOrdenDTO;
import com.example.todotechproject.dto.OrdenVenta.OrdenVentaDTO;
import com.example.todotechproject.dto.ProductoDTO;
import com.example.todotechproject.servicios.DetalleOrdenServicios.DetalleOrdenServicio;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/detalle-orden")
@CrossOrigin(origins = "http://localhost:4200")
public class DetalleOrdenControlador {

    @Autowired
    private DetalleOrdenServicio detalleOrdenServicio;

    @PostMapping("/crear")
    public ResponseEntity<DetalleOrdenDTO> crearDetalle(
            @RequestBody Map<String, Object> datos) {

        // Convertir datos de entrada
        ObjectMapper mapper = new ObjectMapper();

        ProductoDTO productoDTO = mapper.convertValue(datos.get("producto"), ProductoDTO.class);
        OrdenVentaDTO ordenVentaDTO = mapper.convertValue(datos.get("ordenVenta"), OrdenVentaDTO.class);
        Integer cantidad = (Integer) datos.get("cantidad");

        DetalleOrdenDTO creado = detalleOrdenServicio.crearDetalle(productoDTO, ordenVentaDTO, cantidad);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }
}