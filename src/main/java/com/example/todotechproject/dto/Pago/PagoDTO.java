package com.example.todotechproject.dto.Pago;

import com.example.todotechproject.dto.OrdenVenta.OrdenVentaDTO;

public record PagoDTO (Long id, OrdenVentaDTO ordenVentaDTO, double monto, String metodoPago) {
}
