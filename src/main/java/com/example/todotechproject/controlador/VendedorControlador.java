package com.example.todotechproject.controlador;


import com.example.todotechproject.dto.TrabajadorDTO;
import com.example.todotechproject.dto.UsuarioDTO.UsuarioDTO;
import com.example.todotechproject.dto.VendedorDTO;
import com.example.todotechproject.modelo.entidades.Trabajador;
import com.example.todotechproject.modelo.entidades.Vendedor;
import com.example.todotechproject.servicios.VendedorServicios.VendedorServicio;
import com.example.todotechproject.utils.Mappers.Usuarios.UsuarioMapper;
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
    private UsuarioMapper usuarioMapper;

    @GetMapping
    public ResponseEntity<List<TrabajadorDTO>> obtenerVendedores() {
        return ResponseEntity.ok(vendedorServicio.listarVendedores());
    }


    @GetMapping("/{id}")
    public ResponseEntity<TrabajadorDTO> obtenerVendedorPorId(@PathVariable Long id) {
        Trabajador vendedor = vendedorServicio.buscarVendedorPorId(id);
        if (vendedor == null) return ResponseEntity.notFound().build();

        // Convertir el Usuario a UsuarioDTO
        UsuarioDTO usuarioDTO = usuarioMapper.toDTO(vendedor.getUsuario());

        // Crear el TrabajadorDTO con el UsuarioDTO
        TrabajadorDTO dto = new TrabajadorDTO(
                vendedor.getId(),
                vendedor.getNombre(),
                vendedor.getCorreo(),
                vendedor.getTelefono(),
                usuarioDTO
        );

        return ResponseEntity.ok(dto);
    }

    @PostMapping("/crear")
    public ResponseEntity<Map<String, String>> crearVendedor(@RequestBody TrabajadorDTO trabajadorDTO) {
        vendedorServicio.crearVendedor(trabajadorDTO);
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Vendedor creado correctamente");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarVendedor(@RequestBody Trabajador trabajador) {
        vendedorServicio.actualizarVendedor(trabajador);
        return ResponseEntity.ok("Vendedor actualizado correctamente");
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarVendedor(@PathVariable Long id) {
        vendedorServicio.eliminarVendedor(id);
        return ResponseEntity.ok("Vendedor eliminado correctamente");
    }

}
