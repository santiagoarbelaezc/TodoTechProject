package com.example.todotechproject.utils.Mappers;

import com.example.todotechproject.dto.TrabajadorDTO;
import com.example.todotechproject.dto.UsuarioDTO.UsuarioDTO;
import com.example.todotechproject.modelo.entidades.Trabajador;
import com.example.todotechproject.modelo.entidades.Usuario;
import org.springframework.stereotype.Component;

@Component
public class TrabajadorMapper {

    public Trabajador toTrabajador(TrabajadorDTO dto) {
        return Trabajador.builder()
                .id(dto.id())
                .nombre(dto.nombre())
                .correo(dto.correo())
                .telefono(dto.telefono())
                .build();
    }

    public TrabajadorDTO toTrabajadorDTO(Trabajador t) {
        return new TrabajadorDTO(
                t.getId(),
                t.getNombre(),
                t.getCorreo(),
                t.getTelefono(),
                new UsuarioDTO(
                        t.getUsuario().getId(),
                        t.getUsuario().getUsuario(),
                        t.getUsuario().getPassword(),
                        t.getUsuario().getTipoUsuario()
                )
        );
    }
}

