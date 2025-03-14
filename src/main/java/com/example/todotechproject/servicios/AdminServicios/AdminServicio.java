package com.example.todotechproject.servicios.AdminServicios;

import com.example.todotechproject.modelo.entidades.*;
import com.example.todotechproject.modelo.entidades.ReporteUsuarios;
import com.example.todotechproject.modelo.entidades.Reportes.ReporteStock;
import com.example.todotechproject.modelo.entidades.Reportes.ReporteVentas;
import com.example.todotechproject.modelo.enums.TipoUsuario;

import java.util.List;

public interface AdminServicio {

    //GESTION DE USUARIOS

    Usuario registrarUsuario(String nombre, String contraseña, TipoUsuario tipo) throws Exception;

    void eliminarUsuario(Long usuarioId) throws Exception;

    void actualizarUsuario(String nombre, String contraseña, TipoUsuario tipo) throws Exception;

    Usuario buscarUsuario(Long usuarioId) throws Exception;

    List<Usuario> listarUsuarios() throws Exception;

    void cambiarEstadoUsuario(Long usuarioId, boolean activo) throws Exception;

    //

    Vendedor crearVendedor (Vendedor vendedor) throws Exception;
    //GESTION DE PRODUCTOS

    Producto registrarProducto(String nombre, String codigo, String descripcion) throws Exception;

    void eliminarProducto(Long productoId) throws Exception;

    void actualizarProducto(String nombre, String codigo, String descripcion) throws Exception;

    //Buscar por LONG en tablas
    Producto buscarProducto(Long productoId) throws Exception;
    //Buscar por codigo en tablas
    Producto buscarProducto(String codigo) throws Exception;


    void actualizarStockProducto(Long productoId, int cantidad) throws Exception;

    void eliminarStockProducto(Long productoId) throws Exception;


    //GESTION ORDENES DE VENTA

    List<OrdenVenta> listarOrdenesVenta() throws Exception;

    OrdenVenta buscarOrdenVenta(Long ordenId) throws Exception;

    void cancelarOrdenVenta(Long ordenId) throws Exception; // Si hubo error en la transacción


    //REPORTES Y ANALISIS DE LOS DATOS

    List<ReporteVentas> generarReporteVentasPorFecha(String fechaInicio, String fechaFin) throws Exception;

    List<ReporteStock> generarReporteStockBajo() throws Exception; // Productos con stock crítico

    List<ReporteUsuarios> generarReporteUsuariosActivos() throws Exception;

    //GESTION INVENTARIO

    void recibirNuevaMercancia(Long productoId, int cantidadRecibida) throws Exception;



    //CONFIGURACION DEL SISTEMA

    void actualizarParametrosSistema(String parametro, String valor) throws Exception; // Ej: cambiar clave de API

    void configurarSeguridad(String tipoSeguridad, String valor) throws Exception; // Configurar roles, accesos






}
