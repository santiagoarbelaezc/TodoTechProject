package com.example.todotechproject.servicios.VendedorServicios;


import com.example.todotechproject.dto.TrabajadorDTO;
import com.example.todotechproject.modelo.entidades.*;
import com.example.todotechproject.modelo.enums.EstadoOrden;
import com.example.todotechproject.repositorios.*;
import com.example.todotechproject.utils.Mappers.TrabajadorMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendedorServicioImp implements VendedorServicio{

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private TrabajadorRepo trabajadorRepo;

    @Autowired
    private OrdenVentaRepo ordenVentaRepo;

    @Autowired
    private ProductoRepo productoRepo;

    @Autowired
    private ClienteRepo clienteRepo;

    @Autowired
    private TrabajadorMapper trabajadorMapper;

    @Override
    public void crearVendedor(TrabajadorDTO trabajadorDTO) {
        Optional<Usuario> usuarioOptional = usuarioRepo.findByUsuario(trabajadorDTO.usuario().usuario());

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            Trabajador trabajador = trabajadorMapper.toTrabajador(trabajadorDTO);
            trabajador.setUsuario(usuario);
            trabajadorRepo.save(trabajador);
        } else {
            throw new RuntimeException("El usuario asociado no fue encontrado");
        }
    }

    @Override
    public OrdenVenta crearOrdenVenta(LocalDateTime fecha, Cliente cliente, Trabajador trabajador) throws Exception {
        Cliente clienteGuardado = clienteRepo.save(cliente);
        OrdenVenta ordenVenta = new OrdenVenta();
        ordenVenta.setFecha(java.sql.Timestamp.valueOf(fecha));
        ordenVenta.setCliente(clienteGuardado);
        ordenVenta.setTrabajador(trabajador); // Asume que OrdenVenta ya usa Trabajador
        ordenVenta.setEstado(EstadoOrden.PENDIENTE);
        ordenVenta.setTotal(0.0);
        ordenVenta.setProductos(List.of());

        return ordenVentaRepo.save(ordenVenta);
    }

    @Override
    public Trabajador buscarVendedorPorUsuario(Usuario usuario) {
        return trabajadorRepo.findByUsuario(usuario)
                .orElseThrow(() -> new RuntimeException("Trabajador no encontrado para el usuario: " + usuario.getUsuario()));
    }

    @Override
    public void cancelarOrdenVenta(Long ordenId) throws Exception {}

    @Override
    public void agregarProducto(Producto producto) throws Exception {}

    @Override
    public void eliminarProducto(Producto producto) throws Exception {}

    @Override
    public void buscarProducto(String codigo) throws Exception {}

    @Override
    public List<Producto> buscarProductoNombre(String nombre) throws Exception {
        return List.of();
    }

    @Override
    public List<Producto> buscarProductoId(String id) throws Exception {
        return List.of();
    }

    @Override
    public List<Producto> buscarProductoCategoria(Categoria categoria) throws Exception {
        return List.of();
    }

    @Override
    public int consultarDisponibilidad(String codigo) throws Exception {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código del producto no puede ser nulo o vacío");
        }

        try {
            Producto producto = productoRepo.findByCodigo(codigo);
            if (producto != null) {
                return producto.getStock();
            } else {
                throw new Exception("El producto con código " + codigo + " no existe");
            }
        } catch (Exception e) {
            throw new Exception("Error al consultar disponibilidad del producto: " + e.getMessage(), e);
        }
    }

    @Override
    public void ingresarDescuento(String codigo, double descuento) throws Exception {}

    @Override
    public void modificarCantidadProducto(String codigo, int nuevaCantidad) throws Exception {}

    @Override
    public void actualizarVendedor(Trabajador trabajador) {
        if (trabajadorRepo.existsById(trabajador.getId())) {
            trabajadorRepo.save(trabajador);
        }
    }

    @Override
    public void eliminarVendedor(Long id) {
        trabajadorRepo.deleteById(id);
    }

    @Override
    public Trabajador buscarVendedorPorId(Long id) {
        return trabajadorRepo.findById(id).orElse(null);
    }

    @Override
    public List<TrabajadorDTO> listarVendedores() {
        return trabajadorRepo.findAll().stream()
                .map(trabajadorMapper::toTrabajadorDTO)
                .collect(Collectors.toList());
    }


}
