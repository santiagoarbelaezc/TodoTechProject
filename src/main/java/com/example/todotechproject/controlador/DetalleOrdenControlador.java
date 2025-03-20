package com.example.todotechproject.controlador;

import com.example.todotechproject.dto.detalleOrden.AgregarProductoDetVenDTO;
import com.example.todotechproject.modelo.entidades.DetalleOrden;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/carrito")
public class DetalleOrdenControlador {

    @PostMapping
    public void agregarProductoDetOrd(@RequestBody AgregarProductoDetVenDTO detalleProducto) throws  Exception{
        DetalleOrden detalleOrden = new DetalleOrden();
        detalleOrden.setProducto(detalleProducto.producto());
        detalleOrden.setCantidad(detalleProducto.cantidad());
        detalleOrden.setSubtotal(detalleProducto.subtotal());
    }
}
