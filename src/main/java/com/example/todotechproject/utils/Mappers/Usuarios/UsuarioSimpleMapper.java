package com.example.todotechproject.utils.Mappers.Usuarios;

import com.example.todotechproject.dto.UsuarioDTO.UsuarioSimpleDTO;
import com.example.todotechproject.modelo.entidades.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioSimpleMapper {

    public UsuarioSimpleDTO toDTO(Usuario usuario) {
        return new UsuarioSimpleDTO(
                usuario.getUsuario(),
                usuario.getPassword(),
                usuario.getTipoUsuario()
        );
    }

    public Usuario toEntity(UsuarioSimpleDTO dto) {
        return Usuario.builder()
                .usuario(dto.usuario())
                .password(dto.password())
                .tipoUsuario(dto.tipoUsuario())
                .build();
    }

    public List<UsuarioSimpleDTO> toDTOList(List<Usuario> usuarios) {
        return usuarios.stream().map(this::toDTO).toList();
    }
}
