package com.example.todotechproject.controlador;

import com.example.todotechproject.dto.OrdenVenta.CrearOrdenDTO;
import com.example.todotechproject.modelo.entidades.OrdenVenta;
import com.example.todotechproject.modelo.entidades.Usuario;
import com.example.todotechproject.modelo.entidades.Vendedor;
import com.example.todotechproject.servicios.UsuarioServicios.UsuarioServicio;
import com.example.todotechproject.servicios.VendedorServicios.VendedorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
@RestController
@RequestMapping("/api/ordenes")
public class OrdenVentaControlador {

    @Autowired
    private VendedorServicio vendedorServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;


    @PostMapping("/crear")
    public ResponseEntity<?> crearOrdenVenta(@RequestBody CrearOrdenDTO request) {
        try {
            Usuario usuario = usuarioServicio.buscarPorUsuario(request.vendedor());

            if (usuario == null) {
                return ResponseEntity.badRequest().body("El usuario no existe");
            }

            Vendedor vendedor = vendedorServicio.buscarVendedorPorUsuario(usuario); // 🔥

            OrdenVenta orden = vendedorServicio.crearOrdenVenta(
                    LocalDateTime.now(),
                    request.cliente(),
                    vendedor // ✅ Ahora sí es un Vendedor
            );

            return ResponseEntity.ok(orden);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear la orden de venta: " + e.getMessage());
        }
    }



}
