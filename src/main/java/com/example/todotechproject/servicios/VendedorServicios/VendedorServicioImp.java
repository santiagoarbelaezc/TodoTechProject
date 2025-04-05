package com.example.todotechproject.servicios.VendedorServicios;

import com.example.todotechproject.dto.VendedorDTO;
import com.example.todotechproject.modelo.entidades.*;
import com.example.todotechproject.modelo.enums.EstadoOrden;
import com.example.todotechproject.repositorios.DetalleOrdenRepo;
import com.example.todotechproject.repositorios.OrdenVentaRepo;
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



    @Override
    public OrdenVenta crearOrdenVenta(LocalDateTime fecha, Cliente cliente, Vendedor vendedor, List<Producto> productoList, EstadoOrden estadoOrden, double total) throws Exception {
        OrdenVenta ordenVenta = new OrdenVenta();
        ordenVenta.setFecha(java.sql.Timestamp.valueOf(fecha)); // Convertir LocalDateTime a Date
        ordenVenta.setCliente(cliente);
        ordenVenta.setVendedor(vendedor);
        ordenVenta.setEstado(estadoOrden);
        ordenVenta.setTotal(total);

        // Guardar la orden primero para generar su ID
        ordenVenta = ordenVentaRepo.save(ordenVenta);

        // Crear y guardar los detalles de la orden
        for (Producto producto : productoList) {
            DetalleOrden detalle = new DetalleOrden();
            detalle.setProducto(producto);
            detalle.setCantidad(1); // Ajusta según la lógica
            detalle.setSubtotal(producto.getPrecio()); // Ajusta si hay descuentos
            detalleOrdenRepo.save(detalle); // Guardar cada detalle en la BD
        }

        return ordenVenta;
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
        // Lógica para consultar la disponibilidad de un producto en el inventario
        return 0;
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
