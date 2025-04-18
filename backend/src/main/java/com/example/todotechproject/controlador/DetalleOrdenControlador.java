package com.example.todotechproject.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todotechproject.modelo.dto.DetalleOrdenDTO;
import com.example.todotechproject.servicios.DetalleOrdenServicios.DetalleOrdenServicio;

@RestController
@RequestMapping("/api/carrito")
public class DetalleOrdenControlador {

  @Autowired
  private DetalleOrdenServicio detalleOrdenServicio;

  @PostMapping
  public void addDetalleVenta(@RequestBody DetalleOrdenDTO detalleProducto) {
    detalleOrdenServicio.save(detalleProducto);
  }

  @GetMapping("/{id}")
  public List<DetalleOrdenDTO> listByOrdenVenta(@PathVariable Long id) {
    return detalleOrdenServicio.findByOrdenVenta(id);
  }

  @DeleteMapping("/{id}")
  public void deleteDetalleVentaDelCarrito(@PathVariable Long id) {
    detalleOrdenServicio.delete(id);
  }

  @GetMapping
  public List<DetalleOrdenDTO> list() {
    return detalleOrdenServicio.findAll();
  }
}
