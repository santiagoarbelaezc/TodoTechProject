package com.example.todotechproject.servicios.ProductoServicios;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.todotechproject.modelo.dto.ProductoDTO;
import com.example.todotechproject.modelo.entidades.Categoria;
import com.example.todotechproject.modelo.entidades.Producto;
import com.example.todotechproject.modelo.mapper.ProductoMapper;
import com.example.todotechproject.repositorios.ProductoRepo;
import com.example.todotechproject.utils.exceptions.NotFoundException;

@Service
public class ProductoServicioImp implements ProductoServicio {

  private final ProductoRepo productoRepo;
  private final ProductoMapper productoMapper;

  public ProductoServicioImp(ProductoRepo productoRepo, ProductoMapper productoMapper) {
    this.productoRepo = productoRepo;
    this.productoMapper = productoMapper;
  }

  @Override
  public List<ProductoDTO> listProducts() {
    return productoRepo.findAll().stream().map(productoMapper::toDto).collect(Collectors.toList());
  }

  @Override
  public List<ProductoDTO> listProductsByCategory(Long idCategory) {
    Categoria categoria = new Categoria();
    categoria.setId(idCategory);
    return productoRepo.findAllByCategoria(categoria).stream().map(productoMapper::toDto).collect(Collectors.toList());
  }

  @Override
  public Optional<ProductoDTO> getProductById(Long id) {
    return productoRepo.findById(id).map(productoMapper::toDto);
  }

  @Override
  public void deleteProduct(Long id) {
    productoRepo.deleteById(id);
  }

  @Override
  public ProductoDTO saveProduct(ProductoDTO producto) {
    return productoMapper.toDto(productoRepo.save(productoMapper.toEntity(producto)));
  }

  @Override
  public ProductoDTO updateProduct(ProductoDTO producto) {
    // Verificar si el producto existe
    Optional<Producto> existingProduct = productoRepo.findById(producto.id());
    if (existingProduct.isEmpty()) {
      throw new NotFoundException("Producto no encontrado id: " + producto.id());
    }
    return productoMapper.toDto(productoRepo.save(productoMapper.toEntity(producto)));
  }
}
