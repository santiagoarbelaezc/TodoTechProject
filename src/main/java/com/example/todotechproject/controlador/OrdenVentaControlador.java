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

  public OrdenVentaControlador(OrdenVentaServicio ordenVentaServicio) {
    this.ordenVentaServicio = ordenVentaServicio;
  }

  @PostMapping
  public OrdenVentaDTO create(@RequestBody @Valid OrdenVentaDTO ordenVentaDTO) throws Exception {
    return ordenVentaServicio.save(ordenVentaDTO);
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
