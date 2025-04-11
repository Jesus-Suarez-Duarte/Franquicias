package com.franquici.franqui.mapper;



import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.franquici.franqui.Dto.ProductoDTO;
import com.franquici.franqui.Dto.ProductoRequestDTO;
import com.franquici.franqui.Entity.Producto;
import com.franquici.franqui.Entity.Sucursal;

@Component
public class ProductoMapper {
 
 public ProductoDTO toDTO(Producto producto) {
     if (producto == null) {
         return null;
     }
     
     ProductoDTO dto = new ProductoDTO();
     dto.setId(producto.getId());
     dto.setNombre(producto.getNombre());
     dto.setStock(producto.getStock());
     dto.setSucursalId(producto.getSucursal().getId());
     
     return dto;
 }
 
 public List<ProductoDTO> toDTOList(List<Producto> productos) {
     return productos.stream()
         .map(this::toDTO)
         .collect(Collectors.toList());
 }
 
 public Producto toEntity(ProductoRequestDTO dto, Sucursal sucursal) {
     if (dto == null) {
         return null;
     }
     
     Producto producto = new Producto();
     producto.setNombre(dto.getNombre());
     producto.setStock(dto.getStock());
     producto.setSucursal(sucursal);
     
     return producto;
 }
 
 public void updateEntity(Producto producto, ProductoRequestDTO dto) {
     if (dto == null) {
         return;
     }
     
     producto.setNombre(dto.getNombre());
     producto.setStock(dto.getStock());
 }
}