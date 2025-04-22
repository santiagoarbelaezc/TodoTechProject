package com.example.todotechproject.controlador;

import com.example.todotechproject.dto.DetalleOrden.CrearDetalleRequest;
import com.example.todotechproject.dto.DetalleOrden.DetalleOrdenDTO;
import com.example.todotechproject.servicios.DetalleOrdenServicios.DetalleOrdenServicioImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/detalles")
@CrossOrigin("*")
public class DetalleOrdenControlador {

    @Autowired
    private DetalleOrdenServicioImp detalleOrdenServicio;

    @PostMapping("/crear")
    public ResponseEntity<DetalleOrdenDTO> crearDetalle(@RequestBody CrearDetalleRequest request) {
        return detalleOrdenServicio.crear(request);
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<String> eliminarDetalle(@RequestBody CrearDetalleRequest request) {
        return detalleOrdenServicio.eliminar(request);
    }

}
