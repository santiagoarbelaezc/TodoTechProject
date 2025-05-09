package com.example.todotechproject.controlador;

import com.example.todotechproject.dto.Reporte.ReporteRendimientoDTO;
import com.example.todotechproject.dto.VendedorDTO;
import com.example.todotechproject.modelo.entidades.Vendedor;
import com.example.todotechproject.servicios.VendedorServicios.VendedorServicio;
import com.example.todotechproject.utils.Mappers.VendedorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vendedores")
public class VendedorControlador {

    @Autowired
    private VendedorServicio vendedorServicio;

    @Autowired
    private VendedorMapper vendedorMapper;


    @GetMapping
    public ResponseEntity<List<VendedorDTO>> obtenerVendedores() {
        return ResponseEntity.ok(vendedorServicio.listarVendedores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendedorDTO> obtenerVendedorPorId(@PathVariable Long id) {
        Vendedor vendedor = vendedorServicio.buscarVendedorPorId(id);
        if (vendedor == null) return ResponseEntity.notFound().build();

        // Utilizando el mapper para convertir el Vendedor a VendedorDTO
        VendedorDTO dto = vendedorMapper.vendedorToVendedorDTO(vendedor);
        return ResponseEntity.ok(dto);
    }


    @PostMapping("/crear")
    public ResponseEntity<Map<String, String>> crearVendedor(@RequestBody VendedorDTO vendedor) {
        vendedorServicio.crearVendedor(vendedor); // reutiliza guardar
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Vendedor creado correctamente");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarVendedor(@RequestBody Vendedor vendedor) {
        vendedorServicio.actualizarVendedor(vendedor);
        return ResponseEntity.ok("Vendedor actualizado correctamente");
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarVendedor(@PathVariable Long id) {
        vendedorServicio.eliminarVendedor(id);
        return ResponseEntity.ok("Vendedor eliminado correctamente");
    }

    @GetMapping("/reporteRendimiento")
    public List<ReporteRendimientoDTO> obtenerReporteRendimiento() {
        return vendedorServicio.obtenerReporteRendimiento();
    }
}
