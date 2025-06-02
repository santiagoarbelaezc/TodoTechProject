package com.example.todotechproject.controlador;

import com.example.todotechproject.dto.TrabajadorDTO;
import com.example.todotechproject.modelo.entidades.Trabajador;
import com.example.todotechproject.servicios.DespachadorServicios.DespachadorServicio;
import com.example.todotechproject.utils.Mappers.Usuarios.UsuarioMapper;
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

    @Autowired
    private UsuarioMapper usuarioMapper;

    @GetMapping
    public ResponseEntity<List<TrabajadorDTO>> obtenerDespachadores() {
        return ResponseEntity.ok(despachadorServicio.listarDespachadores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrabajadorDTO> obtenerDespachadorPorId(@PathVariable Long id) {
        Trabajador despachador = despachadorServicio.buscarDespachadorPorId(id);
        if (despachador == null) return ResponseEntity.notFound().build();

        TrabajadorDTO dto = new TrabajadorDTO(
                despachador.getId(),
                despachador.getNombre(),
                despachador.getCorreo(),
                despachador.getTelefono(),
                usuarioMapper.toDTO(despachador.getUsuario())
        );

        return ResponseEntity.ok(dto);
    }

    @PostMapping("/crear")
    public ResponseEntity<Map<String, String>> crearDespachador(@RequestBody TrabajadorDTO despachador) {
        despachadorServicio.guardarDespachador(despachador);
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Despachador creado correctamente");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarDespachador(@RequestBody Trabajador despachador) {
        despachadorServicio.actualizarDespachador(despachador);
        return ResponseEntity.ok("Despachador actualizado correctamente");
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarDespachador(@PathVariable Long id) {
        despachadorServicio.eliminarDespachador(id);
        return ResponseEntity.ok("Despachador eliminado correctamente");
    }
}
