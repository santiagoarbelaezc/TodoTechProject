package com.example.todotechproject.servicios.DetalleOrdenServicios;

import com.example.todotechproject.dto.detalleOrden.AgregarProductoDetVenDTO;
import com.example.todotechproject.modelo.entidades.DetalleOrden;
import org.springframework.web.bind.annotation.RequestBody;

public interface DetalleOrdenServicio {

    DetalleOrden agregarProductoDetOrd(AgregarProductoDetVenDTO detalleProducto);
}
