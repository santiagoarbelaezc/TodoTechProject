package com.example.todotechproject.servicios.OrdenVentaServicios;

import com.example.todotechproject.dto.OrdenVenta.CrearOrdenDTO;
import com.example.todotechproject.dto.OrdenVenta.OrdenVentaDTO;
import com.example.todotechproject.modelo.entidades.OrdenVenta;
import com.example.todotechproject.modelo.entidades.Usuario;
import com.example.todotechproject.modelo.entidades.Vendedor;
import com.example.todotechproject.repositorios.OrdenVentaRepo;
import com.example.todotechproject.servicios.UsuarioServicios.UsuarioServicio;
import com.example.todotechproject.servicios.VendedorServicios.VendedorServicio;
import com.example.todotechproject.utils.Mappers.OrdenVentaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrdenVentaServicioImp implements OrdenVentaServicio {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private VendedorServicio vendedorServicio;

    @Autowired
    private OrdenVentaRepo ordenVentaRepo;


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


    @Override
    public OrdenVentaDTO obtenerUltimaOrden() {
        OrdenVenta ultimaOrden = ordenVentaRepo.findTopByOrderByIdDesc();

        // ⚠️ Forzar carga de relaciones perezosas
        ultimaOrden.getCliente().getNombre();
        ultimaOrden.getVendedor().getNombre();
        ultimaOrden.getProductos().forEach(p -> {
            p.getProducto().getId(); // Carga producto asociado
            p.getOrdenVenta().getId(); // Puede que no lo necesites
        });

        return OrdenVentaMapper.toDTO(ultimaOrden);
    }
}

