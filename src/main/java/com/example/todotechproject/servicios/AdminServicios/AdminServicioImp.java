package com.example.todotechproject.servicios.AdminServicios;

import com.example.todotechproject.modelo.entidades.*;

import com.example.todotechproject.modelo.enums.TipoUsuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServicioImp implements AdminServicio{


    @Override
    public Usuario registrarUsuario(String nombre, String contraseña, TipoUsuario tipo) throws Exception {
        return null;
    }

    @Override
    public void eliminarUsuario(Long usuarioId) throws Exception {

    }

    @Override
    public void asignarPermisos(Long usuarioId, TipoUsuario tipo) throws Exception {

    }

    @Override
    public void actualizarUsuario(String nombre, String contraseña, TipoUsuario tipo) throws Exception {

    }

    @Override
    public Usuario buscarUsuario(Long usuarioId) throws Exception {
        return null;
    }

    @Override
    public List<Usuario> listarUsuarios() throws Exception {
        return List.of();
    }

    @Override
    public void cambiarEstadoUsuario(Long usuarioId, boolean activo) throws Exception {

    }



    @Override
    public Producto registrarProducto(String nombre, String codigo, String descripcion) throws Exception {
        return null;
    }

    @Override
    public void eliminarProducto(Long productoId) throws Exception {

    }

    @Override
    public void actualizarProducto(String nombre, String codigo, String descripcion) throws Exception {

    }

    @Override
    public Producto buscarProducto(Long productoId) throws Exception {
        return null;
    }

    @Override
    public Producto buscarProducto(String codigo) throws Exception {
        return null;
    }

    @Override
    public void actualizarStockProducto(Long productoId, int cantidad) throws Exception {

    }

    @Override
    public void eliminarStockProducto(Long productoId) throws Exception {

    }

    @Override
    public List<OrdenVenta> listarOrdenesVenta() throws Exception {
        return List.of();
    }

    @Override
    public OrdenVenta buscarOrdenVenta(Long ordenId) throws Exception {
        return null;
    }

    @Override
    public void cancelarOrdenVenta(Long ordenId) throws Exception {

    }

    @Override
    public void recibirNuevaMercancia(Long productoId, int cantidadRecibida) throws Exception {

    }

    @Override
    public void actualizarParametrosSistema(String parametro, String valor) throws Exception {

    }

    @Override
    public void configurarSeguridad(String tipoSeguridad, String valor) throws Exception {

    }
}
