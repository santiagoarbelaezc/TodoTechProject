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
        if (pago.getOrden() == null || pago.getOrden().getId() == null) {
            throw new IllegalArgumentException("La orden asociada al pago no es válida");
        }

        // Obtener la orden asociada
        OrdenVenta orden = ordenVentaRepo.findById(pago.getOrden().getId())
                .orElseThrow(() -> new IllegalArgumentException("Orden no encontrada"));

        // Actualizar el estado a PAGADA
        orden.setEstado(EstadoOrden.PAGADA);
        ordenVentaRepo.save(orden); // Guardar cambios

        // Guardar el pago
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
