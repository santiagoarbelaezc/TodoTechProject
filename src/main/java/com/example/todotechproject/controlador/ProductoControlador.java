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

import com.example.todotechproject.modelo.dto.ProductoDTO;
import com.example.todotechproject.servicios.ProductoServicios.ProductoServicio;

@RestController
@RequestMapping("/api/producto")
public class ProductoControlador {

  private final ProductoServicio productoServicio;

  public ProductoControlador(ProductoServicio productoServicio) {
    this.productoServicio = productoServicio;
  }

  // Crear usuario
  @PostMapping
  public ProductoDTO createProduct(@RequestBody @Valid ProductoDTO productoDTO) throws Exception {
    return productoServicio.saveProduct(productoDTO);
  }

  @PutMapping
  public ProductoDTO updateUsuario(@RequestBody @Valid ProductoDTO productoDTO) throws Exception {
    return productoServicio.updateProduct(productoDTO);
  }

  @GetMapping
  public List<ProductoDTO> listProduct() {
    return productoServicio.listProducts();
  }
  @DeleteMapping("/{id}")
  public void deleteProduct(@PathVariable Long id) {
     productoServicio.deleteProduct(id);
  }

  @GetMapping("/{id}")
  public void getProduct(@PathVariable Long id) {
    productoServicio.getProductById(id);
  }
}
