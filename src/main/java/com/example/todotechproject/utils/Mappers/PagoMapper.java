package com.example.todotechproject.utils.Mappers;

import com.example.todotechproject.dto.Pago.CrearPagoDTO;
import com.example.todotechproject.dto.Pago.PagoDTO;
import com.example.todotechproject.modelo.entidades.Pago;
import com.example.todotechproject.modelo.enums.MedioPago;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PagoMapper {


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
        pago.setOrden(OrdenVentaMapper.toEntity(dto.ordenVentaDTO()));
        pago.setMonto(dto.monto());
        pago.setMetodoPago(MedioPago.valueOf(dto.metodoPago()));
        return pago;
    }

    public List<PagoDTO> toDTOList(List<Pago> pagos) {
        return pagos.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
