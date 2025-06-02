package com.example.todotechproject.controlador;

import com.example.todotechproject.dto.TrabajadorDTO;
import com.example.todotechproject.servicios.VendedorServicios.VendedorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vendedores")
public class VendedorControlador {

    @Autowired
    private VendedorServicio vendedorServicio;

    @GetMapping
    public ResponseEntity<List<TrabajadorDTO>> obtenerVendedores() {
        return ResponseEntity.ok(vendedorServicio.listarVendedores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrabajadorDTO> obtenerVendedorPorId(@PathVariable Long id) {
        TrabajadorDTO dto = vendedorServicio.obtenerVendedorDTOPorId(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/crear")
    public ResponseEntity<Map<String, String>> crearVendedor(@RequestBody TrabajadorDTO trabajadorDTO) {
        return ResponseEntity.ok(vendedorServicio.crearVendedorYMensaje(trabajadorDTO));
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarVendedor(@RequestBody TrabajadorDTO trabajadorDTO) {
        vendedorServicio.actualizarVendedorDTO(trabajadorDTO);
        return ResponseEntity.ok("Vendedor actualizado correctamente");
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarVendedor(@PathVariable Long id) {
        vendedorServicio.eliminarVendedor(id);
        return ResponseEntity.ok("Vendedor eliminado correctamente");
    }
}
