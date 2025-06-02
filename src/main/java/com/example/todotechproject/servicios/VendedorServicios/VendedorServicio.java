package com.example.todotechproject.servicios.VendedorServicios;


import com.example.todotechproject.dto.TrabajadorDTO;

import com.example.todotechproject.modelo.entidades.*;


import java.time.LocalDateTime;
import java.util.List;

public interface VendedorServicio {


    //SERVICIOS DEL EMPLEADO
    //SPRINT 1

    OrdenVenta crearOrdenVenta(LocalDateTime fecha, Cliente cliente, Trabajador trabajador) throws Exception;

    Trabajador buscarVendedorPorUsuario(Usuario usuario);

    void cancelarOrdenVenta(Long ordenId) throws Exception;

    void agregarProducto(Producto producto) throws Exception;

    void eliminarProducto(Producto producto) throws Exception;

    void buscarProducto(String codigo) throws Exception;

    List<Producto> buscarProductoNombre(String nombre)throws Exception;
    List<Producto> buscarProductoId(String id)throws Exception;
    List<Producto> buscarProductoCategoria(Categoria categoria)throws Exception;

    int consultarDisponibilidad (String codigo) throws Exception;

    void ingresarDescuento (String codigo, double descuento) throws Exception;

    void modificarCantidadProducto(String codigo, int nuevaCantidad) throws Exception;


    void actualizarVendedor(Trabajador trabajador);

    void eliminarVendedor(Long id);

    Trabajador buscarVendedorPorId(Long id);

    List<TrabajadorDTO> listarVendedores();

    void crearVendedor(TrabajadorDTO trabajadorDTO);
}
