package com.example.todotechproject.modelo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.todotechproject.modelo.dto.ClienteDTO;
import com.example.todotechproject.modelo.entidades.Cliente;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

  ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

  ClienteDTO toDto(Cliente cliente);

  Cliente toEntity(ClienteDTO clienteDTO);
}
