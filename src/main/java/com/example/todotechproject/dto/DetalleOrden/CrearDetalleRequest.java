package com.example.todotechproject.dto.DetalleOrden;

import com.example.todotechproject.dto.ProductoDTO;
import lombok.Data;

@Data
public class CrearDetalleRequest {
    private ProductoDTO producto;
    private Long ordenVentaId;
}


