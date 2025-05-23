package com.example.todotechproject.servicios.ProductoServicios;

import com.example.todotechproject.dto.ProductoDTO;
import com.example.todotechproject.dto.ProductoReporteRequest;

import java.util.List;

public interface ProductoServicio {

    ProductoDTO crearProducto(ProductoDTO productoDTO);

    List<ProductoDTO> obtenerTodos();

    ProductoDTO buscarPorId(Long id);

    List<ProductoDTO> buscarPorNombre(String nombre);

    List<ProductoDTO> buscarPorCategoria(String categoria);

    List<ProductoDTO> obtenerProductosAsus();

    List<ProductoDTO> obtenerProductosIphone();

    List<ProductoDTO> obtenerProductosHp();

    List<ProductoDTO> obtenerProductosSamsung();

    void eliminarProducto(Long id);

    List<ProductoReporteRequest> obtenerReporteVentasPorProducto();

    ProductoDTO actualizarProducto(Long id, ProductoDTO productoDTO);
}
