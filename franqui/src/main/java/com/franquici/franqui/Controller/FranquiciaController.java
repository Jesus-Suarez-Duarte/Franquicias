package com.franquici.franqui.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.franquici.franqui.Dto.FranquiciaDTO;
import com.franquici.franqui.Dto.FranquiciaRequestDTO;
import com.franquici.franqui.Service.FranquiciaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/franquicias")
public class FranquiciaController {
 
 @Autowired
 private FranquiciaService franquiciaService;
 
 @GetMapping
 public ResponseEntity<List<FranquiciaDTO>> getAllFranquicias() {
     List<FranquiciaDTO> franquicias = franquiciaService.getAllFranquicias();
     return new ResponseEntity<>(franquicias, HttpStatus.OK);
 }
 
 @GetMapping("/{id}")
 public ResponseEntity<FranquiciaDTO> getFranquiciaById(@PathVariable Long id) {
     FranquiciaDTO franquicia = franquiciaService.getFranquiciaById(id);
     return new ResponseEntity<>(franquicia, HttpStatus.OK);
 }
 
 @PostMapping
 public ResponseEntity<FranquiciaDTO> createFranquicia(@Valid @RequestBody FranquiciaRequestDTO franquiciaDTO) {
     FranquiciaDTO createdFranquicia = franquiciaService.createFranquicia(franquiciaDTO);
     return new ResponseEntity<>(createdFranquicia, HttpStatus.CREATED);
 }
 
 @PutMapping("/{id}")
 public ResponseEntity<FranquiciaDTO> updateFranquicia(@PathVariable Long id, 
                                                  @Valid @RequestBody FranquiciaRequestDTO franquiciaDTO) {
     FranquiciaDTO updatedFranquicia = franquiciaService.updateFranquicia(id, franquiciaDTO);
     return new ResponseEntity<>(updatedFranquicia, HttpStatus.OK);
 }
 
 @DeleteMapping("/{id}")
 public ResponseEntity<Void> deleteFranquicia(@PathVariable Long id) {
     franquiciaService.deleteFranquicia(id);
     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
 }
}