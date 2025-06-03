package com.example.todotechproject.servicios.PagoServicios;

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
        // Validación básica de entrada
        if (pago == null) {
            throw new IllegalArgumentException("El pago no puede ser nulo");
        }

        if (pago.getOrden() == null || pago.getOrden().getId() == null) {
            throw new IllegalArgumentException("La orden asociada al pago es inválida o está incompleta");
        }

        if (pago.getMonto() == null || pago.getMonto() <= 0) {
            throw new IllegalArgumentException("El monto del pago debe ser mayor a cero");
        }

        if (pago.getMetodoPago() == null) {
            throw new IllegalArgumentException("Debe especificar el método de pago");
        }

        // Verificar que la orden existe
        OrdenVenta orden = ordenVentaRepo.findById(pago.getOrden().getId())
                .orElseThrow(() -> new IllegalArgumentException("No se encontró una orden con el ID proporcionado"));

        // Verificar si la orden ya ha sido pagada o cancelada
        if (orden.getEstado() == EstadoOrden.PAGADA) {
            throw new IllegalStateException("Esta orden ya ha sido pagada");
        }
        if (orden.getEstado() == EstadoOrden.CANCELADA) {
            throw new IllegalStateException("No se puede registrar un pago para una orden cancelada");
        }

        // Validar que no se haya registrado ya un pago para esta orden
        boolean yaPagada = pagoRepo.findAll().stream()
                .anyMatch(p -> p.getOrden().getId().equals(orden.getId()));
        if (yaPagada) {
            throw new IllegalStateException("Ya existe un pago registrado para esta orden");
        }

        // Validar que el monto corresponda al total de la orden
        if (!pago.getMonto().equals(orden.getTotal())) {
            throw new IllegalArgumentException("El monto del pago no coincide con el total de la orden (" + orden.getTotal() + ")");
        }

        // Actualizar el estado de la orden
        orden.setEstado(EstadoOrden.PAGADA);
        ordenVentaRepo.save(orden);

        // Guardar el pago
        pago.setOrden(orden); // Asegurarse de usar la entidad gestionada
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
