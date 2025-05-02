package com.franquici.franqui.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.franquici.franqui.Dto.FranquiciaDTO;
import com.franquici.franqui.Dto.FranquiciaRequestDTO;
import com.franquici.franqui.Service.FranquiciaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/franquicias")
@Tag(name = "Franquicias", description = "API para gestión de franquicias")
public class FranquiciaController {
 
    @Autowired
    private FranquiciaService franquiciaService;
    
    @Operation(summary = "Obtener todas las franquicias", description = "Retorna una lista con todas las franquicias registradas")
    @ApiResponse(responseCode = "200", description = "Lista de franquicias obtenida con éxito")
    @GetMapping
    public ResponseEntity<List<FranquiciaDTO>> getAllFranquicias() {
        List<FranquiciaDTO> franquicias = franquiciaService.getAllFranquicias();
        return new ResponseEntity<>(franquicias, HttpStatus.OK);
    }
    
    @Operation(summary = "Obtener franquicia por ID", description = "Retorna una franquicia según el ID proporcionado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Franquicia encontrada",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = FranquiciaDTO.class))),
        @ApiResponse(responseCode = "404", description = "Franquicia no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<FranquiciaDTO> getFranquiciaById(@PathVariable Long id) {
        FranquiciaDTO franquicia = franquiciaService.getFranquiciaById(id);
        return new ResponseEntity<>(franquicia, HttpStatus.OK);
    }
    
    @Operation(summary = "Crear nueva franquicia", description = "Crea una nueva franquicia con los datos proporcionados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Franquicia creada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de franquicia inválidos")
    })
    @PostMapping
    public ResponseEntity<FranquiciaDTO> createFranquicia(@Valid @RequestBody FranquiciaRequestDTO franquiciaDTO) {
        FranquiciaDTO createdFranquicia = franquiciaService.createFranquicia(franquiciaDTO);
        return new ResponseEntity<>(createdFranquicia, HttpStatus.CREATED);
    }
    
    @Operation(summary = "Actualizar franquicia", description = "Actualiza una franquicia existente según el ID proporcionado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Franquicia actualizada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de franquicia inválidos"),
        @ApiResponse(responseCode = "404", description = "Franquicia no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<FranquiciaDTO> updateFranquicia(
            @PathVariable Long id, 
            @Valid @RequestBody FranquiciaRequestDTO franquiciaDTO) {
        FranquiciaDTO updatedFranquicia = franquiciaService.updateFranquicia(id, franquiciaDTO);
        return new ResponseEntity<>(updatedFranquicia, HttpStatus.OK);
    }
    
    @Operation(summary = "Eliminar franquicia", description = "Elimina una franquicia según el ID proporcionado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Franquicia eliminada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Franquicia no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFranquicia(@PathVariable Long id) {
        franquiciaService.deleteFranquicia(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}