package com.example.todotechproject.modelo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.todotechproject.modelo.dto.VendedorDTO;
import com.example.todotechproject.modelo.entidades.Vendedor;

@Mapper(componentModel = "spring")
public interface VendedorMapper {
  VendedorMapper INSTANCE = Mappers.getMapper(VendedorMapper.class);
  VendedorDTO toDto(Vendedor vendedor);
  Vendedor toEntity(VendedorDTO vendedorDTO);
}
