package com.example.todotechproject.utils.Mappers;

import com.example.todotechproject.dto.UsuarioDTO.UsuarioDTO;
import com.example.todotechproject.dto.VendedorDTO;
import com.example.todotechproject.modelo.entidades.Usuario;
import com.example.todotechproject.modelo.entidades.Vendedor;
import org.springframework.stereotype.Component;

@Component // Anotación para que Spring lo reconozca como un bean
public class VendedorMapper {

    // Método para convertir un Vendedor a un VendedorDTO
    public VendedorDTO vendedorToVendedorDTO(Vendedor vendedor) {
        if (vendedor == null) {
            return null;
        }

        UsuarioDTO usuarioDTO = new UsuarioDTO(
                vendedor.getUsuario().getId(),
                vendedor.getUsuario().getUsuario(),
                vendedor.getUsuario().getPassword(),
                vendedor.getUsuario().getTipoUsuario()
        );

        return new VendedorDTO(
                vendedor.getId(),
                vendedor.getNombre(),
                vendedor.getCorreo(),
                vendedor.getTelefono(),
                usuarioDTO
        );
    }

    // Método para convertir un VendedorDTO a un Vendedor
    public Vendedor vendedorDTOToVendedor(VendedorDTO vendedorDTO) {
        if (vendedorDTO == null) {
            return null;
        }

        return new Vendedor(
                vendedorDTO.id(),
                vendedorDTO.nombre(),
                vendedorDTO.correo(),
                vendedorDTO.telefono(),
                new Usuario(
                        vendedorDTO.usuario().id(),
                        vendedorDTO.usuario().usuario(),
                        vendedorDTO.usuario().password(),
                        vendedorDTO.usuario().tipoUsuario()
                )
        );
    }
}
