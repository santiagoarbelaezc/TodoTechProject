package com.example.todotechproject.controlador;

import com.example.todotechproject.dto.TrabajadorDTO;
import com.example.todotechproject.dto.UsuarioDTO.UsuarioDTO;
import com.example.todotechproject.modelo.entidades.Trabajador;
import com.example.todotechproject.servicios.CajeroServicios.CajeroServicio;
import com.example.todotechproject.utils.Mappers.Usuarios.UsuarioMapper;
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

    @Autowired
    private UsuarioMapper usuarioMapper;

    @GetMapping
    public ResponseEntity<List<TrabajadorDTO>> obtenerCajeros() {
        return ResponseEntity.ok(cajeroServicio.listarCajeros());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrabajadorDTO> obtenerCajeroPorId(@PathVariable Long id) {
        Trabajador cajero = cajeroServicio.buscarCajeroPorId(id);
        if (cajero == null) return ResponseEntity.notFound().build();

        UsuarioDTO usuarioDTO = usuarioMapper.toDTO(cajero.getUsuario());

        TrabajadorDTO dto = new TrabajadorDTO(
                cajero.getId(),
                cajero.getNombre(),
                cajero.getCorreo(),
                cajero.getTelefono(),
                usuarioDTO
        );

        return ResponseEntity.ok(dto);
    }

    @PostMapping("/crear")
    public ResponseEntity<Map<String, String>> crearCajero(@RequestBody TrabajadorDTO trabajadorDTO) {
        cajeroServicio.guardarCajero(trabajadorDTO);
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Cajero creado correctamente");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarCajero(@RequestBody Trabajador trabajador) {
        cajeroServicio.actualizarCajero(trabajador);
        return ResponseEntity.ok("Cajero actualizado correctamente");
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarCajero(@PathVariable Long id) {
        cajeroServicio.eliminarCajero(id);
        return ResponseEntity.ok("Cajero eliminado correctamente");
    }
}
