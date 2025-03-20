package com.example.todotechproject.modelo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.todotechproject.modelo.dto.OrdenVentaDTO;
import com.example.todotechproject.modelo.entidades.OrdenVenta;

@Mapper(componentModel = "spring")
public interface OrdenVentaMapper {

  OrdenVentaMapper INSTANCE = Mappers.getMapper(OrdenVentaMapper.class);

  OrdenVentaDTO toDTO(OrdenVenta ordenVenta);

  OrdenVenta toEntity(OrdenVentaDTO ordenVentaDTO);
}
