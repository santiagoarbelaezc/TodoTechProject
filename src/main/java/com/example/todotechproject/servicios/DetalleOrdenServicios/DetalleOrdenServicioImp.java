package com.example.todotechproject.servicios.DetalleOrdenServicios;

import com.example.todotechproject.dto.detalleOrden.AgregarProductoDetVenDTO;
import com.example.todotechproject.modelo.entidades.DetalleOrden;
import com.example.todotechproject.repositorios.DetalleOrdenRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DetalleOrdenServicioImp implements DetalleOrdenServicio {

    private final DetalleOrdenRepo detalleOrdenRepo;

    @Override
    public DetalleOrden agregarProductoDetOrd(AgregarProductoDetVenDTO detalleProducto) {
        DetalleOrden detalleOrden = new DetalleOrden();
        detalleOrden.setProducto(detalleProducto.producto());
        detalleOrden.setCantidad(detalleProducto.cantidad());
        detalleOrden.setSubtotal(detalleProducto.subtotal());

        return detalleOrdenRepo.save(detalleOrden);
    }
}
