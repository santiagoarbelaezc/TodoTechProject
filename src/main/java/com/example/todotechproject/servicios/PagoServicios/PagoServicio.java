package com.example.todotechproject.servicios.PagoServicios;

import com.example.todotechproject.modelo.entidades.Pago;

import java.util.List;

public interface PagoServicio {
    void crearPago(Pago pago);
    List<Pago> listarPagos();
    Pago buscarPorId(Long id);
    void eliminarPago(Long id);
}
