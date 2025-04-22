package com.example.todotechproject.utils.Mappers;

import com.example.todotechproject.dto.UsuarioDTO.UsuarioDTO;
import com.example.todotechproject.modelo.entidades.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioMapper {
    public UsuarioDTO toDTO(Usuario usuario) {
        return new UsuarioDTO(usuario.getId(),usuario.getUsuario(), usuario.getPassword(), usuario.getTipoUsuario());
    }

    public Usuario toEntity(UsuarioDTO dto) {
        return Usuario.builder()
                .id(dto.id()) // ✅ Se añade el ID
                .usuario(dto.usuario())
                .password(dto.password())
                .tipoUsuario(dto.tipoUsuario())
                .build();
    }

    public List<UsuarioDTO> toDTOList(List<Usuario> usuarios) {
        return usuarios.stream().map(this::toDTO).collect(Collectors.toList());
    }
}