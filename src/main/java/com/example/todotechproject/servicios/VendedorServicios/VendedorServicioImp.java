package com.example.todotechproject.servicios.VendedorServicios;

import com.example.todotechproject.dto.Reporte.ReporteRendimientoDTO;
import com.example.todotechproject.dto.UsuarioDTO.UsuarioDTO;
import com.example.todotechproject.dto.VendedorDTO;
import com.example.todotechproject.modelo.entidades.*;
import com.example.todotechproject.modelo.enums.EstadoOrden;
import com.example.todotechproject.repositorios.*;
import com.example.todotechproject.utils.Mappers.VendedorMapper;
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
    private VendedorRepo vendedorRepo;

    @Autowired
    private OrdenVentaRepo ordenVentaRepo;

    @Autowired
    private ProductoRepo productoRepo;

    @Autowired
    private ClienteRepo clienteRepo;

    @Autowired
    private VendedorMapper vendedorMapper;

    @Override
    public void crearVendedor(VendedorDTO vendedorDTO) {
        // Recuperar el usuario por su nombre de usuario (o ID si lo manejas así)
        Optional<Usuario> usuarioOptional = usuarioRepo.findByUsuario(vendedorDTO.usuario().usuario());

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();

            // Mapear DTO a entidad
            Vendedor vendedor = vendedorMapper.vendedorDTOToVendedor(vendedorDTO);
            vendedor.setUsuario(usuario); // Aquí se asegura que el usuario es persistente

            vendedorRepo.save(vendedor);
        } else {
            throw new RuntimeException("El usuario asociado no fue encontrado");
        }
    }

    @Override
    public OrdenVenta crearOrdenVenta(LocalDateTime fecha, Cliente cliente, Vendedor vendedor) throws Exception {
        Cliente clienteGuardado = clienteRepo.save(cliente);
        OrdenVenta ordenVenta = new OrdenVenta();
        ordenVenta.setFecha(java.sql.Timestamp.valueOf(fecha));
        ordenVenta.setCliente(clienteGuardado);
        ordenVenta.setVendedor(vendedor);
        ordenVenta.setEstado(EstadoOrden.PENDIENTE); // Estado por defecto
        ordenVenta.setTotal(0.0); // Total vacío por defecto
        ordenVenta.setProductos(List.of()); // Lista vacía de productos

        return ordenVentaRepo.save(ordenVenta);
    }

    @Override
    public Vendedor buscarVendedorPorUsuario(Usuario usuario) {
        return vendedorRepo.findByUsuario(usuario)
                .orElseThrow(() -> new RuntimeException("Vendedor no encontrado para el usuario: " + usuario.getUsuario()));
    }


    @Override
    public void cancelarOrdenVenta(Long ordenId) throws Exception {
        // Lógica para cancelar una orden (se debería interactuar con un repositorio de OrdenVenta)
    }

    @Override
    public void agregarProducto(Producto producto) throws Exception {
        // Lógica para agregar un producto al inventario
    }

    @Override
    public void eliminarProducto(Producto producto) throws Exception {
        // Lógica para eliminar un producto del inventario
    }

    @Override
    public void buscarProducto(String codigo) throws Exception {
        // Lógica para buscar un producto por código en el inventario
    }

    //JUAN DAVID

    @Override
    public List<Producto> buscarProductoNombre(String nombre) throws Exception {
        return List.of();
    }

    //JUAN DAVID

    @Override
    public List<Producto> buscarProductoId(String id) throws Exception {
        return List.of();
    }

    //JUAN DAVID

    @Override
    public List<Producto> buscarProductoCategoria(Categoria categoria) throws Exception {
        return List.of();
    }

    @Override
    public int consultarDisponibilidad(String codigo) throws Exception {
        // Lógica para consultar la disponibilidad de un producto en el inventario //ELI
        //Validar que el código no sea nulo o vacío
        if (codigo == null || codigo.trim().isEmpty()){
            throw new IllegalArgumentException("El código del producto no puede ser nulo o vacío");
        }

        try {
            //Buscar producto por código
            Producto producto = productoRepo.findByCodigo(codigo);

            if(producto != null){
                return producto.getStock();
            } else {
                throw new Exception("El producto con código " + codigo + " no existe");
            }
        }catch (Exception e) {
            throw new Exception("Error al consultar la disponibilidad del producto: " + e.getMessage(), e);
        }

    }

    @Override
    public void ingresarDescuento(String codigo, double descuento) throws Exception {
        // Lógica para ingresar un descuento a un producto
    }

    @Override
    public void modificarCantidadProducto(String codigo, int nuevaCantidad) throws Exception {
        // Lógica para modificar la cantidad de un producto en el inventario
    }

    @Override
    public void actualizarVendedor(Vendedor vendedor) {
        if (vendedorRepo.existsById(vendedor.getId())) {
            vendedorRepo.save(vendedor);
        }
    }

    @Override
    public void eliminarVendedor(Long id) {
        vendedorRepo.deleteById(id);
    }

    @Override
    public Vendedor buscarVendedorPorId(Long id) {
        Optional<Vendedor> vendedor = vendedorRepo.findById(id);
        return vendedor.orElse(null);
    }

    @Override
    public List<VendedorDTO> listarVendedores() {
        List<Vendedor> vendedores = vendedorRepo.findAll();
        return vendedores.stream()
                .map(v -> new VendedorDTO(
                        v.getId(),
                        v.getNombre(),
                        v.getCorreo(),
                        v.getTelefono(),
                        new UsuarioDTO(
                                v.getUsuario().getId(),
                                v.getUsuario().getUsuario(),
                                v.getUsuario().getPassword(),
                                v.getUsuario().getTipoUsuario()
                        )
                ))
                .collect(Collectors.toList());
    }


    @Override
    public List<ReporteRendimientoDTO> obtenerReporteRendimiento() {
        // Obtenemos todos los vendedores
        List<Vendedor> vendedores = vendedorRepo.findAll();

        // Generamos y ordenamos los ReporteRendimientoDTO por totalVentas descendente
        return vendedores.stream()
                .map(vendedor -> {
                    // Obtenemos las órdenes de venta del vendedor
                    List<OrdenVenta> ordenes = ordenVentaRepo.findByVendedor(vendedor);

                    // Sumamos el total de ventas del vendedor
                    double totalVentas = ordenes.stream()
                            .mapToDouble(orden -> orden.getProductos().stream()
                                    .mapToDouble(DetalleOrden::getSubtotal)
                                    .sum())
                            .sum();

                    // Creamos el DTO del vendedor
                    VendedorDTO vendedorDTO = new VendedorDTO(
                            vendedor.getId(),
                            vendedor.getNombre(),
                            vendedor.getCorreo(),
                            vendedor.getTelefono(),
                            new UsuarioDTO(
                                    vendedor.getUsuario().getId(),
                                    vendedor.getUsuario().getUsuario(),
                                    vendedor.getUsuario().getPassword(),
                                    vendedor.getUsuario().getTipoUsuario()
                            )
                    );

                    return new ReporteRendimientoDTO(vendedorDTO, totalVentas);
                })
                // Ordenamos por totalVentas descendente
                .sorted((r1, r2) -> Double.compare(r2.totalVentas(), r1.totalVentas()))
                .collect(Collectors.toList());
    }




}
