package com.example.todotechproject.servicios.VendedorServicios;

import com.example.todotechproject.dto.VendedorDTO;
import com.example.todotechproject.modelo.entidades.*;
import com.example.todotechproject.modelo.enums.EstadoOrden;

import java.time.LocalDateTime;
import java.util.List;

public interface VendedorServicio {


    //SERVICIOS DEL EMPLEADO
    //SPRINT 1

    OrdenVenta crearOrdenVenta(LocalDateTime fecha, Cliente cliente, Vendedor vendedor) throws Exception;

    Vendedor buscarVendedorPorUsuario(Usuario usuario);

    void cancelarOrdenVenta(Long ordenId) throws Exception;

    void agregarProducto(Producto producto) throws Exception;

    void eliminarProducto(Producto producto) throws Exception;

    void buscarProducto(String codigo) throws Exception;

    void buscarProductoNombre(String nombre)throws Exception;

    int consultarDisponibilidad (String codigo) throws Exception;

    void ingresarDescuento (String codigo, double descuento) throws Exception;

    void modificarCantidadProducto(String codigo, int nuevaCantidad) throws Exception;


    void actualizarVendedor(Vendedor vendedor);

    void eliminarVendedor(Long id);

    Vendedor buscarVendedorPorId(Long id);

    List<VendedorDTO> listarVendedores();
}
