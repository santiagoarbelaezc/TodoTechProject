package com.example.todotechproject.servicios.OrdenVentaServicios;

import com.example.todotechproject.dto.detalleOrden.AgregarProductoDetVenDTO;
import com.example.todotechproject.modelo.entidades.DetalleOrden;
import org.springframework.web.bind.annotation.RequestBody;

public interface OrdenVentaServicio {

    void agregarProductoDetOrd(AgregarProductoDetVenDTO detalleProducto) throws  Exception;
}
