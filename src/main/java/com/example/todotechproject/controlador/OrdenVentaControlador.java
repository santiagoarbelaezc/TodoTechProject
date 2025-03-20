package com.example.todotechproject.controlador;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;


import jakarta.validation.Valid;

import com.example.todotechproject.modelo.dto.OrdenVentaDTO;
import com.example.todotechproject.modelo.entidades.OrdenVenta;
import com.example.todotechproject.modelo.entidades.Usuario;
import com.example.todotechproject.modelo.entidades.Vendedor;
import com.example.todotechproject.servicios.OrdenVentaServicios.OrdenVentaServicio;
import com.example.todotechproject.servicios.UsuarioServicios.UsuarioServicio;
import com.example.todotechproject.servicios.VendedorServicios.VendedorServicio;

@RestController
@RequestMapping("/api/orden-venta")
public class OrdenVentaControlador {
  private final OrdenVentaServicio ordenVentaServicio;

  @Autowired
  private VendedorServicio vendedorServicio;

  @Autowired
  private UsuarioServicio usuarioServicio;

  public OrdenVentaControlador(OrdenVentaServicio ordenVentaServicio) {
    this.ordenVentaServicio = ordenVentaServicio;
  }

  @PostMapping
  public ResponseEntity<?>  create(@RequestBody @Valid OrdenVentaDTO ordenVentaDTO) throws Exception {
    try {
      Usuario usuario = usuarioServicio.buscarPorUsuario(request.vendedor());

      if (usuario == null) {
        return ResponseEntity.badRequest().body("El usuario no existe");
      }

      Vendedor vendedor = vendedorServicio.buscarVendedorPorUsuario(usuario); // 🔥

      OrdenVenta orden = vendedorServicio.crearOrdenVenta(
          LocalDateTime.now(),
          ordenVentaDTO.cliente(),
          vendedor // ✅ Ahora sí es un Vendedor
      );

      return ResponseEntity.ok(orden);

    } catch (Exception e) {
      return ResponseEntity.badRequest().body("Error al crear la orden de venta: " + e.getMessage());
    }
  }

  @PutMapping
  public OrdenVentaDTO update(@RequestBody @Valid OrdenVentaDTO ordenVentaDTO) throws Exception {
    return ordenVentaServicio.updateOrder(ordenVentaDTO);
  }

  @GetMapping
  public List<OrdenVentaDTO> listProduct() {
    return ordenVentaServicio.list();
  }
  @DeleteMapping("/{id}")
  public void deleteOrdenVenta(@PathVariable Long id) {
    ordenVentaServicio.delete(id);
  }

  @GetMapping("/{id}")
  public void getOrdenVenta(@PathVariable Long id) {
    ordenVentaServicio.getOrderById(id);
  }
}
