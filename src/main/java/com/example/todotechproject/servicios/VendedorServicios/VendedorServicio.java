package com.example.todotechproject.servicios.VendedorServicios;

import java.time.LocalDateTime;
import java.util.List;

import com.example.todotechproject.modelo.dto.VendedorDTO;
import com.example.todotechproject.modelo.entidades.Categoria;
import com.example.todotechproject.modelo.entidades.Cliente;
import com.example.todotechproject.modelo.entidades.OrdenVenta;
import com.example.todotechproject.modelo.entidades.Producto;
import com.example.todotechproject.modelo.entidades.Vendedor;
import com.example.todotechproject.modelo.enums.EstadoOrden;

public interface VendedorServicio {


    //SERVICIOS DEL EMPLEADO
    //SPRINT 1

  OrdenVenta crearOrdenVenta(LocalDateTime fecha, Cliente cliente, Vendedor vendedor, List<Producto> productoList
      , EstadoOrden estadoOrden, double total) throws Exception;

    void cancelarOrdenVenta(Long ordenId) throws Exception;

    void agregarProducto(Producto producto) throws Exception;

    void eliminarProducto(Producto producto) throws Exception;

  List<Producto> buscarProductoPorCodigo(String codigo) throws Exception;

  List<Producto> buscarProductoPorNombre(String nombre) throws Exception;

  List<Producto> buscarProductorPorCategoria(Categoria categoria) throws Exception;

  int consultarDisponibilidad(String codigo) throws Exception;

  void ingresarDescuento(String codigo, double descuento) throws Exception;

    void modificarCantidadProducto(String codigo, int nuevaCantidad) throws Exception;

  void actualizarVendedor(Vendedor vendedor);

    void eliminarVendedor(Long id);

    Vendedor buscarVendedorPorId(Long id);

    List<VendedorDTO> listarVendedores();
}
