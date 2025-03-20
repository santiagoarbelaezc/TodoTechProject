package com.example.todotechproject.modelo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.todotechproject.modelo.dto.DetalleOrdenDTO;
import com.example.todotechproject.modelo.entidades.DetalleOrden;

@Mapper(componentModel = "spring")
public interface DetalleOrdenMapper {

  DetalleOrdenMapper INSTANCE = Mappers.getMapper(DetalleOrdenMapper.class);

  @Mapping(source = "ordenVenta.id", target = "idOrdenVenta")

  DetalleOrdenDTO toDto(DetalleOrden detalleOrden);

  @Mapping(source = "idOrdenVenta", target = "ordenVenta.id")

  DetalleOrden toEntity(DetalleOrdenDTO detalleOrdenDTO);

}
