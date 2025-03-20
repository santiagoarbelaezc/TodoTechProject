package com.example.todotechproject.servicios.ProductoServicios;

import java.util.List;
import java.util.Optional;

import com.example.todotechproject.modelo.dto.ProductoDTO;

public interface ProductoServicio {

  ProductoDTO saveProduct(ProductoDTO producto);

  ProductoDTO updateProduct(ProductoDTO producto);

  List<ProductoDTO> listProducts();

  List<ProductoDTO> listProductsByCategory(Long idCategory);

  Optional<ProductoDTO> getProductById(Long id);

  void deleteProduct(Long id);
}
