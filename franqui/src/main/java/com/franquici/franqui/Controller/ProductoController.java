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
import com.franquici.franqui.Dto.ProductoUpdateDTO;
import com.franquici.franqui.Dto.StockUpdateRequestDTO;
import com.franquici.franqui.Service.ProductoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/productos")
@Tag(name = "Productos", description = "API para gestión de productos")
public class ProductoController {
 
    @Autowired
    private ProductoService productoService;
    
    @Operation(summary = "Obtener todos los productos", description = "Retorna una lista con todos los productos registrados")
    @ApiResponse(responseCode = "200", description = "Lista de productos obtenida con éxito")
    @GetMapping
    public ResponseEntity<List<ProductoDTO>> getAllProductos() {
        List<ProductoDTO> productos = productoService.getAllProductos();
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }
    
    @Operation(summary = "Obtener productos por sucursal", description = "Retorna los productos de una sucursal específica")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de productos obtenida con éxito"),
        @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    })
    @GetMapping("/sucursal/{sucursalId}")
    public ResponseEntity<List<ProductoDTO>> getProductosBySucursalId(@PathVariable Long sucursalId) {
        List<ProductoDTO> productos = productoService.getProductosBySucursalId(sucursalId);
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }
    
    @Operation(summary = "Obtener producto por ID", description = "Retorna un producto según el ID proporcionado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto encontrado",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductoDTO.class))),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> getProductoById(@PathVariable Long id) {
        ProductoDTO producto = productoService.getProductoById(id);
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }
    
    @Operation(summary = "Crear nuevo producto", description = "Crea un nuevo producto con los datos proporcionados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Producto creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de producto inválidos"),
        @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    })
    @PostMapping
    public ResponseEntity<ProductoDTO> createProducto(@Valid @RequestBody ProductoRequestDTO productoDTO) {
        ProductoDTO createdProducto = productoService.createProducto(productoDTO);
        return new ResponseEntity<>(createdProducto, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar nombre de producto", description = "Actualiza solo el nombre de un producto existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto actualizado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de producto inválidos"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> updateProducto(
            @PathVariable Long id, 
            @Valid @RequestBody ProductoUpdateDTO productoUpdateDTO) {
        productoUpdateDTO.setId(id);
        ProductoDTO updatedProducto = productoService.updateProductoNombre(productoUpdateDTO);
        return new ResponseEntity<>(updatedProducto, HttpStatus.OK);
    }
    
    @Operation(summary = "Actualizar stock de producto", description = "Actualiza solo el stock de un producto existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Stock actualizado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de stock inválidos"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @PatchMapping("/{id}/stock")
    public ResponseEntity<ProductoDTO> updateProductoStock(
            @PathVariable Long id, 
            @Valid @RequestBody StockUpdateRequestDTO stockDTO) {
        ProductoDTO updatedProducto = productoService.updateProductoStock(id, stockDTO);
        return new ResponseEntity<>(updatedProducto, HttpStatus.OK);
    }
    
    @Operation(summary = "Eliminar producto", description = "Elimina un producto según el ID proporcionado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteProducto(@PathVariable Long id) {
        productoService.deleteProducto(id);
        
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Producto eliminado correctamente");
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @Operation(summary = "Obtener productos con mayor stock por franquicia", 
               description = "Retorna los productos con mayor stock para una franquicia específica")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de productos obtenida con éxito"),
        @ApiResponse(responseCode = "404", description = "Franquicia no encontrada")
    })
    @GetMapping("/franquicia/{franquiciaId}/mayor-stock")
    public ResponseEntity<List<ProductoDTO>> getProductosConMayorStockPorFranquicia(@PathVariable Long franquiciaId) {
        List<ProductoDTO> productos = productoService.getProductosConMayorStockPorFranquicia(franquiciaId);
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }
}