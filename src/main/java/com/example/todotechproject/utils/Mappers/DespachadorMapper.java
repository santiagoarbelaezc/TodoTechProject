package com.example.todotechproject.utils.Mappers;

import com.example.todotechproject.dto.DespachadorDTO;
import com.example.todotechproject.dto.UsuarioDTO.UsuarioDTO;
import com.example.todotechproject.modelo.entidades.Despachador;
import com.example.todotechproject.modelo.entidades.Usuario;
import org.springframework.stereotype.Component;

@Component // Anotación para que Spring lo reconozca como un bean
public class DespachadorMapper {

    // Método para convertir un Despachador a un DespachadorDTO
    public DespachadorDTO despachadorToDespachadorDTO(Despachador despachador) {
        if (despachador == null) {
            return null;
        }

        UsuarioDTO usuarioDTO = new UsuarioDTO(
                despachador.getUsuario().getId(),
                despachador.getUsuario().getUsuario(),
                despachador.getUsuario().getPassword(),
                despachador.getUsuario().getTipoUsuario()
        );

        return new DespachadorDTO(
                despachador.getId(),
                despachador.getNombre(),
                despachador.getCorreo(),
                despachador.getTelefono(),
                usuarioDTO
        );
    }

    // Método para convertir un DespachadorDTO a un Despachador
    public Despachador despachadorDTOToDespachador(DespachadorDTO despachadorDTO) {
        if (despachadorDTO == null) {
            return null;
        }

        return new Despachador(
                despachadorDTO.id(),
                despachadorDTO.nombre(),
                despachadorDTO.correo(),
                despachadorDTO.telefono(),
                new Usuario(
                        despachadorDTO.usuario().id(),
                        despachadorDTO.usuario().usuario(),
                        despachadorDTO.usuario().password(),
                        despachadorDTO.usuario().tipoUsuario()
                )
        );
    }
}
