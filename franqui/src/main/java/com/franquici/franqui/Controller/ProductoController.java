package com.franquici.franqui.Controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.franquici.franqui.Dto.ProductoDTO;
import com.franquici.franqui.Dto.ProductoRequestDTO;
import com.franquici.franqui.Dto.StockUpdateRequestDTO;
import com.franquici.franqui.Service.ProductoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
 
 @Autowired
 private ProductoService productoService;
 
 @GetMapping
 public ResponseEntity<List<ProductoDTO>> getAllProductos() {
     List<ProductoDTO> productos = productoService.getAllProductos();
     return new ResponseEntity<>(productos, HttpStatus.OK);
 }
 
 @GetMapping("/sucursal/{sucursalId}")
 public ResponseEntity<List<ProductoDTO>> getProductosBySucursalId(@PathVariable Long sucursalId) {
     List<ProductoDTO> productos = productoService.getProductosBySucursalId(sucursalId);
     return new ResponseEntity<>(productos, HttpStatus.OK);
 }
 
 @GetMapping("/{id}")
 public ResponseEntity<ProductoDTO> getProductoById(@PathVariable Long id) {
     ProductoDTO producto = productoService.getProductoById(id);
     return new ResponseEntity<>(producto, HttpStatus.OK);
 }
 
 @PostMapping
 public ResponseEntity<ProductoDTO> createProducto(@Valid @RequestBody ProductoRequestDTO productoDTO) {
     ProductoDTO createdProducto = productoService.createProducto(productoDTO);
     return new ResponseEntity<>(createdProducto, HttpStatus.CREATED);
 }
 
 @PutMapping("/{id}")
 public ResponseEntity<ProductoDTO> updateProducto(@PathVariable Long id, 
                                              @Valid @RequestBody ProductoRequestDTO productoDTO) {
     ProductoDTO updatedProducto = productoService.updateProducto(id, productoDTO);
     return new ResponseEntity<>(updatedProducto, HttpStatus.OK);
 }
 
 @PatchMapping("/{id}/stock")
 public ResponseEntity<ProductoDTO> updateProductoStock(@PathVariable Long id, 
                                                  @Valid @RequestBody StockUpdateRequestDTO stockDTO) {
     ProductoDTO updatedProducto = productoService.updateProductoStock(id, stockDTO);
     return new ResponseEntity<>(updatedProducto, HttpStatus.OK);
 }
 
 @DeleteMapping("/{id}")
 public ResponseEntity<Map<String, String>> deleteProducto(@PathVariable Long id) {
     productoService.deleteProducto(id);
     
     // Crear un mapa para el mensaje de respuesta
     Map<String, String> response = new HashMap<>();
     response.put("mensaje", "Producto eliminado correctamente");
     
     // Devolver el mensaje con código HTTP 200 OK
     return new ResponseEntity<>(response, HttpStatus.OK);
 }
 
 @GetMapping("/franquicia/{franquiciaId}/mayor-stock")
 public ResponseEntity<List<ProductoDTO>> getProductosConMayorStockPorFranquicia(@PathVariable Long franquiciaId) {
     List<ProductoDTO> productos = productoService.getProductosConMayorStockPorFranquicia(franquiciaId);
     return new ResponseEntity<>(productos, HttpStatus.OK);
 }
}