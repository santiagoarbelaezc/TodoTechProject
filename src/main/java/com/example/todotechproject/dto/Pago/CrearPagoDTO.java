package com.example.todotechproject.dto.Pago;

public record CrearPagoDTO(
        Long ordenId,  // Cambiamos de OrdenVentaDTO a solo el ID
        double monto,
        String metodoPago
) {}