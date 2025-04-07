package com.example.todotechproject.servicios.OrdenVentaServicios;

import com.example.todotechproject.dto.OrdenVenta.CrearOrdenDTO;
import com.example.todotechproject.modelo.entidades.Cliente;
import com.example.todotechproject.modelo.entidades.OrdenVenta;
import com.example.todotechproject.modelo.entidades.Usuario;
import com.example.todotechproject.modelo.entidades.Vendedor;
import com.example.todotechproject.modelo.enums.EstadoOrden;
import com.example.todotechproject.repositorios.ClienteRepo;
import com.example.todotechproject.repositorios.OrdenVentaRepo;
import com.example.todotechproject.servicios.UsuarioServicios.UsuarioServicio;
import com.example.todotechproject.servicios.VendedorServicios.VendedorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrdenVentaServicioImp implements OrdenVentaServicio {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private VendedorServicio vendedorServicio;


    @Override
    public boolean crearOrdenVentaDesdeDTO(CrearOrdenDTO request) {
        return false;
    }

    @Override
    public OrdenVenta crearOrdenVenta(CrearOrdenDTO request) throws Exception {
        Usuario usuario = usuarioServicio.buscarPorUsuario(request.vendedor());
        if (usuario == null) {
            throw new com.example.todotechproject.excepciones.UsuarioNoEncontradoException("El usuario no existe");
        }

        Vendedor vendedor = vendedorServicio.buscarVendedorPorUsuario(usuario);

        return vendedorServicio.crearOrdenVenta(
                LocalDateTime.now(),
                request.cliente(),
                vendedor
        );
    }
}
