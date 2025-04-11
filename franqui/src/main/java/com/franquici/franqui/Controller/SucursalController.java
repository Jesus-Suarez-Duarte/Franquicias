package com.franquici.franqui.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.franquici.franqui.Dto.SucursalDTO;
import com.franquici.franqui.Dto.SucursalRequestDTO;
import com.franquici.franqui.Service.SucursalService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/sucursales")
public class SucursalController {
 
 @Autowired
 private SucursalService sucursalService;
 
 @GetMapping
 public ResponseEntity<List<SucursalDTO>> getAllSucursales() {
     List<SucursalDTO> sucursales = sucursalService.getAllSucursales();
     return new ResponseEntity<>(sucursales, HttpStatus.OK);
 }
 
 @GetMapping("/franquicia/{franquiciaId}")
 public ResponseEntity<List<SucursalDTO>> getSucursalesByFranquiciaId(@PathVariable Long franquiciaId) {
     List<SucursalDTO> sucursales = sucursalService.getSucursalesByFranquiciaId(franquiciaId);
     return new ResponseEntity<>(sucursales, HttpStatus.OK);
 }
 
 @GetMapping("/{id}")
 public ResponseEntity<SucursalDTO> getSucursalById(@PathVariable Long id) {
     SucursalDTO sucursal = sucursalService.getSucursalById(id);
     return new ResponseEntity<>(sucursal, HttpStatus.OK);
 }
 
 @PostMapping
 public ResponseEntity<SucursalDTO> createSucursal(@Valid @RequestBody SucursalRequestDTO sucursalDTO) {
     SucursalDTO createdSucursal = sucursalService.createSucursal(sucursalDTO);
     return new ResponseEntity<>(createdSucursal, HttpStatus.CREATED);
 }
 
 @PutMapping("/{id}")
 public ResponseEntity<SucursalDTO> updateSucursal(@PathVariable Long id, 
                                              @Valid @RequestBody SucursalRequestDTO sucursalDTO) {
     SucursalDTO updatedSucursal = sucursalService.updateSucursal(id, sucursalDTO);
     return new ResponseEntity<>(updatedSucursal, HttpStatus.OK);
 }
 
 @DeleteMapping("/{id}")
 public ResponseEntity<Void> deleteSucursal(@PathVariable Long id) {
     sucursalService.deleteSucursal(id);
     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
 }
}

