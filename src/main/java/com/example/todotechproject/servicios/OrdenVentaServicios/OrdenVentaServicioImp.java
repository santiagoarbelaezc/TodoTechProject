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
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrdenVentaServicioImp implements OrdenVentaServicio {

    private final UsuarioServicio usuarioServicio;
    private final VendedorServicio vendedorServicio;
    private final ClienteRepo clienteRepo;
    private final OrdenVentaRepo ordenVentaRepo;

    public OrdenVentaServicioImp(UsuarioServicio usuarioServicio,
                                 VendedorServicio vendedorServicio,
                                 ClienteRepo clienteRepo,
                                 OrdenVentaRepo ordenVentaRepo) {
        this.usuarioServicio = usuarioServicio;
        this.vendedorServicio = vendedorServicio;
        this.clienteRepo = clienteRepo;
        this.ordenVentaRepo = ordenVentaRepo;
    }

    @Override
    public boolean crearOrdenVentaDesdeDTO(CrearOrdenDTO request) {
        try {
            Usuario usuario = usuarioServicio.buscarPorUsuario(request.vendedor());
            if (usuario == null) return false;

            Vendedor vendedor = vendedorServicio.buscarVendedorPorUsuario(usuario);
            if (vendedor == null) return false;

            Cliente clienteGuardado = clienteRepo.save(request.cliente());

            OrdenVenta orden = new OrdenVenta();
            orden.setFecha(Timestamp.valueOf(LocalDateTime.now()));
            orden.setCliente(clienteGuardado);
            orden.setVendedor(vendedor);
            orden.setEstado(EstadoOrden.PENDIENTE);
            orden.setTotal(0.0);
            orden.setProductos(List.of());

            ordenVentaRepo.save(orden);
            return true;

        } catch (Exception e) {
            return false;
        }
    }
}
