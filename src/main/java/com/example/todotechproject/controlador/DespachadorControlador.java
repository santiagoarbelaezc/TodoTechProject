package com.example.todotechproject.controlador;

import com.example.todotechproject.dto.DespachadorDTO;
import com.example.todotechproject.dto.VendedorDTO;
import com.example.todotechproject.modelo.entidades.Despachador;
import com.example.todotechproject.modelo.entidades.Vendedor;
import com.example.todotechproject.servicios.DespachadorServicios.DespachadorServicio;
import com.example.todotechproject.servicios.VendedorServicios.VendedorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/despachadores")
public class DespachadorControlador {

    @Autowired
    private DespachadorServicio despachadorServicio;

    @GetMapping
    public ResponseEntity<List<DespachadorDTO>> obtenerDespachadores() {
        return ResponseEntity.ok(despachadorServicio.listarDespachadores());
    }

    @PostMapping("/crear")
    public ResponseEntity<Map<String, String>> crearDespachador(@RequestBody DespachadorDTO despachador) {
        despachadorServicio.guardarDespachador(despachador); // reutiliza guardar
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Despachador creado correctamente");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarDespachador(@RequestBody Despachador despachador) {
        despachadorServicio.actualizarDespachador(despachador);
        return ResponseEntity.ok("Despachador actualizado correctamente");
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarDespachador(@PathVariable Long id) {
        despachadorServicio.eliminarDespachador(id);
        return ResponseEntity.ok("Despachador eliminado correctamente");
    }
}
