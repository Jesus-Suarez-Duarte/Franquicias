package com.franquici.franqui.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.franquici.franqui.Dto.SucursalDTO;
import com.franquici.franqui.Dto.SucursalRequestDTO;
import com.franquici.franqui.Dto.SucursalUpdateDTO;
import com.franquici.franqui.Service.SucursalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/sucursales")
@Tag(name = "Sucursales", description = "API para gestión de sucursales")
public class SucursalController {
 
    @Autowired
    private SucursalService sucursalService;
    
    @Operation(summary = "Obtener todas las sucursales", description = "Retorna una lista con todas las sucursales registradas")
    @ApiResponse(responseCode = "200", description = "Lista de sucursales obtenida con éxito")
    @GetMapping
    public ResponseEntity<List<SucursalDTO>> getAllSucursales() {
        List<SucursalDTO> sucursales = sucursalService.getAllSucursales();
        return new ResponseEntity<>(sucursales, HttpStatus.OK);
    }
    
    @Operation(summary = "Obtener sucursales por franquicia", description = "Retorna las sucursales de una franquicia específica")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de sucursales obtenida con éxito"),
        @ApiResponse(responseCode = "404", description = "Franquicia no encontrada")
    })
    @GetMapping("/franquicia/{franquiciaId}")
    public ResponseEntity<List<SucursalDTO>> getSucursalesByFranquiciaId(@PathVariable Long franquiciaId) {
        List<SucursalDTO> sucursales = sucursalService.getSucursalesByFranquiciaId(franquiciaId);
        return new ResponseEntity<>(sucursales, HttpStatus.OK);
    }
    
    @Operation(summary = "Obtener sucursal por ID", description = "Retorna una sucursal según el ID proporcionado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sucursal encontrada",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = SucursalDTO.class))),
        @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<SucursalDTO> getSucursalById(@PathVariable Long id) {
        SucursalDTO sucursal = sucursalService.getSucursalById(id);
        return new ResponseEntity<>(sucursal, HttpStatus.OK);
    }
    
    @Operation(summary = "Crear nueva sucursal", description = "Crea una nueva sucursal con los datos proporcionados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Sucursal creada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de sucursal inválidos"),
        @ApiResponse(responseCode = "404", description = "Franquicia no encontrada")
    })
    @PostMapping
    public ResponseEntity<SucursalDTO> createSucursal(@Valid @RequestBody SucursalRequestDTO sucursalDTO) {
        SucursalDTO createdSucursal = sucursalService.createSucursal(sucursalDTO);
        return new ResponseEntity<>(createdSucursal, HttpStatus.CREATED);
    }
    
    @Operation(summary = "Actualizar nombre de sucursal", description = "Actualiza solo el nombre de una sucursal existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sucursal actualizada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de sucursal inválidos"),
        @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<SucursalDTO> updateSucursal(
            @PathVariable Long id, 
            @Valid @RequestBody SucursalUpdateDTO sucursalUpdateDTO) {
        sucursalUpdateDTO.setId(id);
        SucursalDTO updatedSucursal = sucursalService.updateSucursalNombre(sucursalUpdateDTO);
        return new ResponseEntity<>(updatedSucursal, HttpStatus.OK);
    }
    
    @Operation(summary = "Eliminar sucursal", description = "Elimina una sucursal según el ID proporcionado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Sucursal eliminada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSucursal(@PathVariable Long id) {
        sucursalService.deleteSucursal(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}