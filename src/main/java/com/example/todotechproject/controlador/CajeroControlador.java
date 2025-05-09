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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Map<String, String>> crearCajero(@RequestBody CajeroDTO cajero) {
        cajeroServicio.guardarCajero(cajero); // reutiliza guardar
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Cajero creado correctamente");
        return ResponseEntity.ok(response);
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
