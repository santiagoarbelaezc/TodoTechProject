package com.example.todotechproject.modelo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.todotechproject.modelo.dto.CategoriaDTO;
import com.example.todotechproject.modelo.entidades.Categoria;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
  CategoriaMapper INSTANCE = Mappers.getMapper(CategoriaMapper.class);
  CategoriaDTO toDto(Categoria categoria);
  Categoria toEntity(CategoriaDTO categoriaDTO);
}
