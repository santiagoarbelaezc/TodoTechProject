package com.example.todotechproject.servicios.VendedorServicios;

import com.example.todotechproject.modelo.entidades.Categoria;
import com.example.todotechproject.modelo.entidades.Producto;

import java.util.List;
import java.util.Optional;

public class VendedorServicioImp implements VendedorServicio{

    public List<Producto> buscarProductosPorNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de búsqueda no puede estar vacío");
        }
        return productoRepositorio.findByNombreContainingIgnoreCase(nombre);
    }

    //Optional con el producto si existe retorna producto sino empty si no existe
    public Optional<Producto> buscarProductoPorCodigo(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El código del producto no puede ser nulo");
        }
        return productoRepositorio.findById(id);
    }

    public List<Producto> buscarProductosPorCategoria(Long categoriaId) {
        if (categoriaId == null) {
            throw new IllegalArgumentException("El ID de la categoría no puede ser nulo");
        }
        return productoRepositorio.findByCategoriaId(categoriaId);
    }

    public List<Producto> buscarProductosPorCategoria(Categoria categoria) {
        if (categoria == null) {
            throw new IllegalArgumentException("La categoría no puede ser null");
        }
        return productoRepositorio.findByCategoria(categoria);
    }

    //Realiza una búsqueda rápida de productos por cualquier criterio (nombre, código o categoría).
    public List<Producto> busquedaRapida(String criterioBusqueda) {
        if (criterioBusqueda == null || criterioBusqueda.trim().isEmpty()) {
            throw new IllegalArgumentException("El criterio de búsqueda no puede estar vacío");
        }

        List<Producto> resultados = productoRepositorio.findByNombreContainingIgnoreCase(criterioBusqueda);

        // Intentar convertir a Long por si es un ID
        try {
            Long id = Long.parseLong(criterioBusqueda);
            Optional<Producto> productoPorId = productoRepositorio.findById(id);
            productoPorId.ifPresent(producto -> {
                if (!resultados.contains(producto)) {
                    resultados.add(producto);
                }
            });
        } catch (NumberFormatException e) {
            // No es un número válido, continuamos con la búsqueda
        }

        // Buscar por categoría (nombre)
        List<Producto> productosPorCategoria = productoRepositorio.findByCategoriaName(criterioBusqueda);
        resultados.addAll(productosPorCategoria.stream()
                .filter(p -> !resultados.contains(p))
                .collect(Collectors.toList()));

        return resultados;
    }

 //Verifica si hay stock disponible de un producto
 public boolean verificarStockDisponible(Long productoId, int cantidad) {
     if (productoId == null) {
         throw new IllegalArgumentException("El ID del producto no puede ser nulo");
     }
     if (cantidad <= 0) {
         throw new IllegalArgumentException("La cantidad debe ser mayor que cero");
     }

     Optional<Producto> producto = productoRepositorio.findById(productoId);
     return producto.map(p -> p.getStock() >= cantidad).orElse(false);
 }

}
