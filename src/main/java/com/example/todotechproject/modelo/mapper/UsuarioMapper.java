package com.example.todotechproject.modelo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.todotechproject.modelo.dto.UsuarioDTO.UsuarioDTO;
import com.example.todotechproject.modelo.entidades.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
  UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);
  UsuarioDTO toDto(Usuario usuario);
  Usuario toEntity(UsuarioDTO usuarioDTO);
}
