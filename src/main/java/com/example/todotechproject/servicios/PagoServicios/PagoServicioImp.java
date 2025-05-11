package com.example.todotechproject.servicios.PagoServicios;

import com.example.todotechproject.modelo.entidades.Pago;
import com.example.todotechproject.repositorios.PagoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagoServicioImp implements PagoServicio {

    @Autowired
    private PagoRepo pagoRepo;

    @Override
    public void crearPago(Pago pago) {
        if (pago.getOrden() == null || pago.getOrden().getId() == null) {
            throw new IllegalArgumentException("La orden asociada al pago no es válida");
        }
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
