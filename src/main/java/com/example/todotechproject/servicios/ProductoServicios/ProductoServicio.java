package com.example.todotechproject.servicios.ProductoServicios;

import com.example.todotechproject.dto.ProductoDTO;

import java.util.List;

public interface ProductoServicio {
    List<ProductoDTO> obtenerTodos();



    ProductoDTO buscarPorId(Long id);

    List<ProductoDTO> buscarPorNombre(String nombre);

    List<ProductoDTO> buscarPorCategoria(String categoria);
}
