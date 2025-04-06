package com.example.todotechproject.controlador;

import com.example.todotechproject.dto.detalleOrden.AgregarProductoDetVenDTO;
import com.example.todotechproject.dto.detalleOrden.DetalleOrdenDTO;
import com.example.todotechproject.modelo.entidades.DetalleOrden;
import com.example.todotechproject.servicios.DetalleOrdenServicios.DetalleOrdenServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/carrito")
@RequiredArgsConstructor
public class DetalleOrdenControlador {

    private final DetalleOrdenServicio detalleOrdenServicio;

    @PostMapping
    public ResponseEntity<DetalleOrdenDTO> agregarProductoDetOrd(@RequestBody AgregarProductoDetVenDTO detalleProducto) {
        Optional<DetalleOrden> optionalDetalle = Optional.ofNullable(detalleOrdenServicio.agregarProductoDetOrd(detalleProducto));

        if (optionalDetalle.isPresent()) {
            DetalleOrden entidad = optionalDetalle.get();
            DetalleOrdenDTO dto = new DetalleOrdenDTO(
                    entidad.getId(),
                    entidad.getProducto(),
                    entidad.getCantidad(),
                    entidad.getSubtotal()
            );
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
