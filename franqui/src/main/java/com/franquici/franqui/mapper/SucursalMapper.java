package com.franquici.franqui.mapper;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.franquici.franqui.Dto.SucursalDTO;
import com.franquici.franqui.Dto.SucursalRequestDTO;
import com.franquici.franqui.Entity.Franquicia;
import com.franquici.franqui.Entity.Sucursal;

@Component
public class SucursalMapper {
 
 @Autowired
 private ProductoMapper productoMapper;
 
 public SucursalDTO toDTO(Sucursal sucursal) {
     if (sucursal == null) {
         return null;
     }
     
     SucursalDTO dto = new SucursalDTO();
     dto.setId(sucursal.getId());
     dto.setNombre(sucursal.getNombre());
     dto.setFranquiciaId(sucursal.getFranquicia().getId());
     
     if (sucursal.getProductos() != null) {
         dto.setProductos(sucursal.getProductos().stream()
             .map(producto -> productoMapper.toDTO(producto))
             .collect(Collectors.toList()));
     }
     
     return dto;
 }
 
 public List<SucursalDTO> toDTOList(List<Sucursal> sucursales) {
     return sucursales.stream()
         .map(this::toDTO)
         .collect(Collectors.toList());
 }
 
 public Sucursal toEntity(SucursalRequestDTO dto, Franquicia franquicia) {
     if (dto == null) {
         return null;
     }
     
     Sucursal sucursal = new Sucursal();
     sucursal.setNombre(dto.getNombre());
     sucursal.setFranquicia(franquicia);
     
     return sucursal;
 }
 
 public void updateEntity(Sucursal sucursal, SucursalRequestDTO dto) {
     if (dto == null) {
         return;
     }
     
     sucursal.setNombre(dto.getNombre());
 }
}
