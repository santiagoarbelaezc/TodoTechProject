package com.example.todotechproject.servicios.DetalleOrdenServicios;

import com.example.todotechproject.dto.DetalleOrdenDTO;
import com.example.todotechproject.dto.OrdenVenta.OrdenVentaDTO;
import com.example.todotechproject.dto.ProductoDTO;
import com.example.todotechproject.modelo.entidades.DetalleOrden;
import com.example.todotechproject.modelo.entidades.OrdenVenta;
import com.example.todotechproject.modelo.entidades.Producto;
import com.example.todotechproject.repositorios.DetalleOrdenRepo;
import com.example.todotechproject.repositorios.OrdenVentaRepo;
import com.example.todotechproject.repositorios.ProductoRepo;
import com.example.todotechproject.servicios.DetalleOrdenServicios.DetalleOrdenServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleOrdenServicioImp implements DetalleOrdenServicio {

     @Autowired
    private DetalleOrdenRepo detalleOrdenRepo;
    @Autowired
    private ProductoRepo productoRepo;
    @Autowired
    private OrdenVentaRepo ordenVentaRepo;

    @Override
    public DetalleOrdenDTO crearDetalle(ProductoDTO productoDTO, OrdenVentaDTO ordenVentaDTO, Integer cantidad) {
        Producto producto = productoRepo.findById(productoDTO.getId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        OrdenVenta orden = ordenVentaRepo.findById(ordenVentaDTO.id())
                .orElseThrow(() -> new RuntimeException("Orden de venta no encontrada"));

        Double subtotal = producto.getPrecio() * cantidad;

        DetalleOrden detalle = new DetalleOrden();
        detalle.setProducto(producto);
        detalle.setOrdenVenta(orden);
        detalle.setCantidad(cantidad);
        detalle.setSubtotal(subtotal);

        detalleOrdenRepo.save(detalle);

        return new DetalleOrdenDTO(
                detalle.getId(),
                producto.getId(),
                cantidad,
                subtotal,
                orden.getId()
        );
    }
}