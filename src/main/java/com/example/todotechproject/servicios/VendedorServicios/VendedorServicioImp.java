package com.example.todotechproject.servicios.VendedorServicios;

import com.example.todotechproject.dto.VendedorDTO;
import com.example.todotechproject.modelo.entidades.*;
import com.example.todotechproject.modelo.enums.EstadoOrden;
import com.example.todotechproject.repositorios.ClienteRepo;
import com.example.todotechproject.repositorios.DetalleOrdenRepo;
import com.example.todotechproject.repositorios.OrdenVentaRepo;
import com.example.todotechproject.repositorios.ProductoRepo;
import com.example.todotechproject.repositorios.VendedorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class VendedorServicioImp implements VendedorServicio{


    @Autowired
    private VendedorRepo vendedorRepo;

    @Autowired
    private OrdenVentaRepo ordenVentaRepo;

    @Autowired
    private DetalleOrdenRepo detalleOrdenRepo;
    @Autowired
    private ProductoRepo productoRepo;

    @Autowired
    private ClienteRepo clienteRepo;



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

    @Override
    public void buscarProductoNombre(String nombre) throws Exception {

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
                .map(v -> new VendedorDTO(v.getNombre(), v.getCorreo(), v.getTelefono(), v.getUsuario()))
                .collect(Collectors.toList());
    }
}
