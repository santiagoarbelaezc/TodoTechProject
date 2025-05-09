package com.example.todotechproject.utils.Mappers;

import org.springframework.stereotype.Component;
import com.example.todotechproject.dto.CajeroDTO;
import com.example.todotechproject.dto.UsuarioDTO.UsuarioDTO;
import com.example.todotechproject.modelo.entidades.Cajero;
import com.example.todotechproject.modelo.entidades.Usuario;

@Component // Anotación para que Spring lo reconozca como un bean
public class CajeroMapper {

    // Método para convertir un Cajero a un CajeroDTO
    public CajeroDTO cajeroToCajeroDTO(Cajero cajero) {
        if (cajero == null) {
            return null;
        }

        UsuarioDTO usuarioDTO = new UsuarioDTO(
                cajero.getUsuario().getId(),
                cajero.getUsuario().getUsuario(),
                cajero.getUsuario().getPassword(),
                cajero.getUsuario().getTipoUsuario()
        );

        return new CajeroDTO(
                cajero.getId(),
                cajero.getNombre(),
                cajero.getCorreo(),
                cajero.getTelefono(),
                usuarioDTO
        );
    }

    // Método para convertir un CajeroDTO a un Cajero
    public Cajero cajeroDTOToCajero(CajeroDTO cajeroDTO) {
        if (cajeroDTO == null) {
            return null;
        }

        return new Cajero(
                cajeroDTO.id(),
                cajeroDTO.nombre(),
                cajeroDTO.correo(),
                cajeroDTO.telefono(),
                new Usuario(
                        cajeroDTO.usuario().id(),
                        cajeroDTO.usuario().usuario(),
                        cajeroDTO.usuario().password(),
                        cajeroDTO.usuario().tipoUsuario()
                )
        );
    }
}
