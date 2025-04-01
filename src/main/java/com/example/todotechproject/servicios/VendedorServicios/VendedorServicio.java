package com.example.todotechproject.servicios.VendedorServicios;

import com.example.todotechproject.modelo.entidades.Categoria;
import com.example.todotechproject.modelo.entidades.Producto;

import java.util.List;
import java.util.Optional;

public interface VendedorServicio {
    List<Producto> buscarProductosPorNombre(String nombre);

    Optional<Producto> buscarProductoPorCodigo(Long id);

    List<Producto> buscarProductosPorCategoria(Long categoriaId);

    List<Producto> buscarProductosPorCategoria(Categoria categoria);

    List<Producto> busquedaRapida(String criterioBusqueda);

    boolean verificarStockDisponible(Long productoId, int cantidad);
}
