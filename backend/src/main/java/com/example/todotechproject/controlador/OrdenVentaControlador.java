package com.example.todotechproject.controlador;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.NonNull;

import com.example.todotechproject.modelo.dto.OrdenVentaDTO;
import com.example.todotechproject.modelo.enums.EstadoOrden;
import com.example.todotechproject.servicios.OrdenVentaServicios.OrdenVentaServicio;

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
  public List<OrdenVentaDTO> listOrdenVenta() {
    return ordenVentaServicio.list();
  }

  @GetMapping("/vendedor/{id}/estado/{estado}")
  public List<OrdenVentaDTO> listrOrvenVentaByVendedorAndStatus(@PathVariable @NonNull Long id, @PathVariable @NonNull EstadoOrden estado){
      return ordenVentaServicio.listByUserAndStatus(id, estado);
    }

    @DeleteMapping("/{id}")
    public void deleteOrdenVenta (@PathVariable Long id){
      ordenVentaServicio.delete(id);
    }

    @GetMapping("/{id}")
    public void getOrdenVenta (@PathVariable Long id){
      ordenVentaServicio.getOrderById(id);
    }
  }
