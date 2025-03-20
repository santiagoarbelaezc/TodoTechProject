package com.example.todotechproject.servicios.ProductoServicios;

import java.util.List;
import java.util.Optional;

import com.example.todotechproject.modelo.dto.ProductoDTO;
import com.example.todotechproject.modelo.entidades.Producto;

public interface ProductoServicio {

  ProductoDTO saveProduct(ProductoDTO producto);

  ProductoDTO updateProduct(ProductoDTO producto);

  List<ProductoDTO> listProducts();

  List<ProductoDTO> listProductsByCategory(Long idCategory);

  Optional<ProductoDTO> getProductById(Long id);

  void deleteProduct(Long id);
}
