package com.example.todotechproject.utils.Mappers.Usuarios;

import com.example.todotechproject.dto.UsuarioDTO.UsuarioDTO2;
import com.example.todotechproject.modelo.entidades.Usuario;
import com.example.todotechproject.modelo.enums.TipoUsuario;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioMapper2 {

    // Convertir de entidad Usuario a UsuarioDTO2
    public UsuarioDTO2 toDTO(Usuario usuario) {
        return new UsuarioDTO2(
                usuario.getUsuario(),
                usuario.getPassword(),
                TipoUsuario.valueOf(String.valueOf(usuario.getTipoUsuario())) // Asumiendo que TipoUsuario es un enum
        );
    }

    // Convertir de UsuarioDTO2 a entidad Usuario
    public Usuario toEntity(UsuarioDTO2 dto) {
        return Usuario.builder()
                .usuario(dto.usuario())
                .password(dto.password())
                .tipoUsuario(TipoUsuario.valueOf(dto.tipoUsuario().toString())) // Asumiendo que `tipoUsuario` es un enum
                .build();
    }

    // Convertir una lista de usuarios a una lista de UsuarioDTO2
    public List<UsuarioDTO2> toDTOList(List<Usuario> usuarios) {
        return usuarios.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
