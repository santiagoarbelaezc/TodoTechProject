package com.example.todotechproject.controlador;

import com.example.todotechproject.dto.CajeroDTO;
import com.example.todotechproject.dto.VendedorDTO;
import com.example.todotechproject.modelo.entidades.Cajero;
import com.example.todotechproject.modelo.entidades.Vendedor;
import com.example.todotechproject.servicios.CajeroServicios.CajeroServicio;
import com.example.todotechproject.servicios.VendedorServicios.VendedorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cajeros")
public class CajeroControlador {

    @Autowired
    private CajeroServicio cajeroServicio;


    @GetMapping
    public ResponseEntity<List<CajeroDTO>> obtenerCajeros() {
        return ResponseEntity.ok(cajeroServicio.listarCajeros());
    }

    @PostMapping("/crear")
    public ResponseEntity<String> crearCajero(@RequestBody CajeroDTO cajero) {
        cajeroServicio.guardarCajero(cajero);
        return ResponseEntity.ok("Cajero creado correctamente");
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarCajero(@RequestBody Cajero cajero) {
        cajeroServicio.actualizarCajero(cajero);
        return ResponseEntity.ok("Cajero actualizado correctamente");
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarCajero(@PathVariable Long id) {
        cajeroServicio.eliminarCajero(id);
        return ResponseEntity.ok("Cajero eliminado correctamente");
    }
}
