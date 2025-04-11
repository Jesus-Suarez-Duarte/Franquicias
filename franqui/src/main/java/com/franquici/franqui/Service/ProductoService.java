package com.franquici.franqui.Service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.franquici.franqui.Dto.ProductoDTO;
import com.franquici.franqui.Dto.ProductoRequestDTO;
import com.franquici.franqui.Dto.StockUpdateRequestDTO;
import com.franquici.franqui.exception.ResourceAlreadyExistsException;
import com.franquici.franqui.exception.ResourceNotFoundException;
import com.franquici.franqui.mapper.ProductoMapper;
import com.franquici.franqui.Entity.Producto;
import com.franquici.franqui.Entity.Sucursal;
import com.franquici.franqui.Repository.ProductoRepository;
import com.franquici.franqui.Repository.SucursalRepository;

@Service
public class ProductoService {
 
 @Autowired
 private ProductoRepository productoRepository;
 
 @Autowired
 private SucursalRepository sucursalRepository;
 
 @Autowired
 private ProductoMapper productoMapper;
 
 @Transactional(readOnly = true)
 public List<ProductoDTO> getAllProductos() {
     List<Producto> productos = productoRepository.findAll();
     return productoMapper.toDTOList(productos);
 }
 
 @Transactional(readOnly = true)
 public List<ProductoDTO> getProductosBySucursalId(Long sucursalId) {
     if (!sucursalRepository.existsById(sucursalId)) {
         throw new ResourceNotFoundException("Sucursal no encontrada con id: " + sucursalId);
     }
     
     List<Producto> productos = productoRepository.findBySucursalId(sucursalId);
     return productoMapper.toDTOList(productos);
 }
 
 @Transactional(readOnly = true)
 public ProductoDTO getProductoById(Long id) {
     Producto producto = productoRepository.findById(id)
         .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con id: " + id));
     return productoMapper.toDTO(producto);
 }
 
 @Transactional
 public ProductoDTO createProducto(ProductoRequestDTO productoDTO) {
     Sucursal sucursal = sucursalRepository.findById(productoDTO.getSucursalId())
         .orElseThrow(() -> new ResourceNotFoundException("Sucursal no encontrada con id: " + productoDTO.getSucursalId()));
     
     if (productoRepository.existsByNombreAndSucursalId(productoDTO.getNombre(), productoDTO.getSucursalId())) {
         throw new ResourceAlreadyExistsException("Ya existe un producto con el nombre: " + productoDTO.getNombre() + 
                                                 " para la sucursal con id: " + productoDTO.getSucursalId());
     }
     
     Producto producto = productoMapper.toEntity(productoDTO, sucursal);
     producto = productoRepository.save(producto);
     return productoMapper.toDTO(producto);
 }
 
 @Transactional
 public ProductoDTO updateProducto(Long id, ProductoRequestDTO productoDTO) {
     Producto producto = productoRepository.findById(id)
         .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con id: " + id));
     
     // Solo verificamos duplicados si el nombre cambió
     if (!producto.getNombre().equals(productoDTO.getNombre()) && 
         productoRepository.existsByNombreAndSucursalId(productoDTO.getNombre(), producto.getSucursal().getId())) {
         throw new ResourceAlreadyExistsException("Ya existe un producto con el nombre: " + productoDTO.getNombre() + 
                                                 " para esta sucursal");
     }
     
     productoMapper.updateEntity(producto, productoDTO);
     producto = productoRepository.save(producto);
     return productoMapper.toDTO(producto);
 }
 
 @Transactional
 public ProductoDTO updateProductoStock(Long id, StockUpdateRequestDTO stockDTO) {
     Producto producto = productoRepository.findById(id)
         .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con id: " + id));
     
     producto.setStock(stockDTO.getNuevoStock());
     producto = productoRepository.save(producto);
     return productoMapper.toDTO(producto);
 }
 
 @Transactional
 public void deleteProducto(Long id) {
     if (!productoRepository.existsById(id)) {
         throw new ResourceNotFoundException("Producto no encontrado con id: " + id);
     }
     productoRepository.deleteById(id);
 }
 
 @Transactional(readOnly = true)
 public List<ProductoDTO> getProductosConMayorStockPorFranquicia(Long franquiciaId) {
     List<Producto> productos = productoRepository.findProductoConMayorStockPorSucursal(franquiciaId);
     return productoMapper.toDTOList(productos);
 }
}
