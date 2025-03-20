package com.example.todotechproject.modelo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.todotechproject.modelo.dto.ProductoDTO;
import com.example.todotechproject.modelo.entidades.Producto;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

  ProductoMapper INSTANCE = Mappers.getMapper(ProductoMapper.class);

  ProductoDTO toDto(Producto producto);

  Producto toEntity(ProductoDTO productoDTO);
}
