package com.example.todotechproject.controlador;

import com.example.todotechproject.dto.Pago.CrearPagoDTO;
import com.example.todotechproject.dto.Pago.PagoDTO;
import com.example.todotechproject.modelo.entidades.Pago;
import com.example.todotechproject.servicios.PagoServicios.PagoServicio;

import com.example.todotechproject.utils.Mappers.PagoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/pagos")
public class PagoControlador {

    @Autowired
    private PagoServicio pagoServicio;

    @Autowired
    private PagoMapper pagoMapper;

    @GetMapping
    public ResponseEntity<List<PagoDTO>> listarPagos() {
        List<PagoDTO> lista = pagoMapper.toDTOList(pagoServicio.listarPagos());
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/crear")
    public ResponseEntity<Map<String, String>> crearPago(@RequestBody CrearPagoDTO crearPagoDTO) {
        pagoServicio.crearPago(pagoMapper.toEntity(crearPagoDTO));
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Pago creado exitosamente");
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagoDTO> obtenerPagoPorId(@PathVariable Long id) {
        Pago pago = pagoServicio.buscarPorId(id);
        if (pago == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pagoMapper.toDTO(pago));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarPago(@PathVariable Long id) {
        pagoServicio.eliminarPago(id);
        return ResponseEntity.ok("Pago eliminado correctamente");
    }
}
