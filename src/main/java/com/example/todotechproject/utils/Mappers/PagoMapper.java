package com.example.todotechproject.utils.Mappers;

import com.example.todotechproject.dto.Pago.CrearPagoDTO;
import com.example.todotechproject.dto.Pago.PagoDTO;
import com.example.todotechproject.modelo.entidades.OrdenVenta;
import com.example.todotechproject.modelo.entidades.Pago;
import com.example.todotechproject.modelo.enums.MedioPago;

import com.example.todotechproject.repositorios.OrdenVentaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class PagoMapper {

    @Autowired
    private OrdenVentaRepo ordenVentaRepository;

    public PagoDTO toDTO(Pago pago) {
        return new PagoDTO(
                pago.getId(),
                OrdenVentaMapper.toDTO(pago.getOrden()),
                pago.getMonto(),
                pago.getMetodoPago().name()
        );
    }

    public Pago toEntity(CrearPagoDTO dto) {
        Pago pago = new Pago();

        // Obtenemos la orden existente por ID
        OrdenVenta orden = ordenVentaRepository.findById(dto.ordenId())
                .orElseThrow(() -> new IllegalArgumentException("Orden no encontrada con ID: " + dto.ordenId()));

        pago.setOrden(orden);
        pago.setMonto(dto.monto());
        pago.setMetodoPago(MedioPago.valueOf(dto.metodoPago()));
        return pago;
    }

    public List<PagoDTO> toDTOList(List<Pago> pagos) {
        return pagos.stream().map(this::toDTO).collect(Collectors.toList());
    }
}