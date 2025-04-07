package com.example.todotechproject.utils.Mappers;

import com.example.todotechproject.dto.ProductoDTO;
import com.example.todotechproject.modelo.entidades.Producto;

public class ProductoMapper {
    public static ProductoDTO toDTO(Producto producto) {
        if (producto == null) return null;

        ProductoDTO dto = new ProductoDTO();
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setCodigo(producto.getCodigo());
        dto.setDescripcion(producto.getDescripcion());
        dto.setCategoria(producto.getCategoria().getNombre());
        dto.setPrecio(producto.getPrecio());
        dto.setStock(producto.getStock());
        dto.setImagen(producto.getImagen());

        return dto;
    }

    // Si necesitas también de DTO a entidad, puedes usar este:
    public static Producto toEntity(ProductoDTO dto) {
        if (dto == null) return null;

        Producto producto = new Producto();
        producto.setId(dto.getId());
        producto.setNombre(dto.getNombre());
        producto.setCodigo(dto.getCodigo());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setImagen(dto.getImagen());
        // Nota: No se puede setear la categoría directamente por nombre sin buscarla en BD

        return producto;
    }
}
