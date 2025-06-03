package com.example.todotechproject.servicios.PagoServicios;

import com.example.todotechproject.excepciones.CajeroExcepciones.OrdenYaPagadaException;
import com.example.todotechproject.excepciones.PagoExcepciones.MontoPagoInvalidoException;
import com.example.todotechproject.excepciones.PagoExcepciones.OrdenNoExisteException;

import com.example.todotechproject.excepciones.PagoExcepciones.PagoInvalidoException;
import com.example.todotechproject.modelo.entidades.OrdenVenta;
import com.example.todotechproject.modelo.entidades.Pago;
import com.example.todotechproject.modelo.enums.EstadoOrden;
import com.example.todotechproject.repositorios.OrdenVentaRepo;
import com.example.todotechproject.repositorios.PagoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagoServicioImp implements PagoServicio {

    @Autowired
    private PagoRepo pagoRepo;

    @Autowired
    private OrdenVentaRepo ordenVentaRepo;

    @Override
    public void crearPago(Pago pago) {
        if (pago == null) {
            throw new PagoInvalidoException("El pago no puede ser nulo.");
        }

        if (pago.getOrden() == null || pago.getOrden().getId() == null) {
            throw new PagoInvalidoException("La orden asociada al pago es inválida o está incompleta.");
        }

        if (pago.getMonto() == null || pago.getMonto() <= 0) {
            throw new MontoPagoInvalidoException("El monto del pago debe ser mayor a cero.");
        }

        if (pago.getMetodoPago() == null) {
            throw new PagoInvalidoException("Debe especificar el método de pago.");
        }

        OrdenVenta orden = ordenVentaRepo.findById(pago.getOrden().getId())
                .orElseThrow(() -> new OrdenNoExisteException("No se encontró una orden con el ID proporcionado."));

        if (orden.getEstado() == EstadoOrden.PAGADA) {
            throw new OrdenYaPagadaException("Esta orden ya ha sido pagada.");
        }

        if (orden.getEstado() == EstadoOrden.CANCELADA) {
            throw new PagoInvalidoException("No se puede registrar un pago para una orden cancelada.");
        }

        boolean yaPagada = pagoRepo.findAll().stream()
                .anyMatch(p -> p.getOrden().getId().equals(orden.getId()));
        if (yaPagada) {
            throw new OrdenYaPagadaException("Ya existe un pago registrado para esta orden.");
        }

        if (!pago.getMonto().equals(orden.getTotal())) {
            throw new MontoPagoInvalidoException("El monto del pago no coincide con el total de la orden (" + orden.getTotal() + ").");
        }

        orden.setEstado(EstadoOrden.PAGADA);
        ordenVentaRepo.save(orden);

        pago.setOrden(orden);
        pagoRepo.save(pago);
    }

    @Override
    public List<Pago> listarPagos() {
        return pagoRepo.findAll();
    }

    @Override
    public Pago buscarPorId(Long id) {
        return pagoRepo.findById(id).orElse(null);
    }

    @Override
    public void eliminarPago(Long id) {
        pagoRepo.deleteById(id);
    }
}
