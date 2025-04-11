package com.franquici.franqui.mapper;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.franquici.franqui.Dto.FranquiciaDTO;
import com.franquici.franqui.Dto.FranquiciaRequestDTO;
import com.franquici.franqui.Entity.Franquicia;

@Component
public class FranquiciaMapper {
 
 @Autowired
 private SucursalMapper sucursalMapper;
 
 public FranquiciaDTO toDTO(Franquicia franquicia) {
     if (franquicia == null) {
         return null;
     }
     
     FranquiciaDTO dto = new FranquiciaDTO();
     dto.setId(franquicia.getId());
     dto.setNombre(franquicia.getNombre());
     
     if (franquicia.getSucursales() != null) {
         dto.setSucursales(franquicia.getSucursales().stream()
             .map(sucursal -> sucursalMapper.toDTO(sucursal))
             .collect(Collectors.toList()));
     }
     
     return dto;
 }
 
 public List<FranquiciaDTO> toDTOList(List<Franquicia> franquicias) {
     return franquicias.stream()
         .map(this::toDTO)
         .collect(Collectors.toList());
 }
 
 public Franquicia toEntity(FranquiciaRequestDTO dto) {
     if (dto == null) {
         return null;
     }
     
     Franquicia franquicia = new Franquicia();
     franquicia.setNombre(dto.getNombre());
     
     return franquicia;
 }
 
 public void updateEntity(Franquicia franquicia, FranquiciaRequestDTO dto) {
     if (dto == null) {
         return;
     }
     
     franquicia.setNombre(dto.getNombre());
 }
}