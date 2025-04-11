package com.franquici.franqui.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.franquici.franqui.Dto.SucursalDTO;
import com.franquici.franqui.Dto.SucursalRequestDTO;
import com.franquici.franqui.exception.ResourceAlreadyExistsException;
import com.franquici.franqui.exception.ResourceNotFoundException;
import com.franquici.franqui.mapper.SucursalMapper;
import com.franquici.franqui.Entity.Franquicia;
import com.franquici.franqui.Entity.Sucursal;
import com.franquici.franqui.Repository.FranquiciaRepository;
import com.franquici.franqui.Repository.SucursalRepository;

@Service
public class SucursalService {
 
 @Autowired
 private SucursalRepository sucursalRepository;
 
 @Autowired
 private FranquiciaRepository franquiciaRepository;
 
 @Autowired
 private SucursalMapper sucursalMapper;
 
 @Transactional(readOnly = true)
 public List<SucursalDTO> getAllSucursales() {
     List<Sucursal> sucursales = sucursalRepository.findAll();
     return sucursalMapper.toDTOList(sucursales);
 }
 
 @Transactional(readOnly = true)
 public List<SucursalDTO> getSucursalesByFranquiciaId(Long franquiciaId) {
     if (!franquiciaRepository.existsById(franquiciaId)) {
         throw new ResourceNotFoundException("Franquicia no encontrada con id: " + franquiciaId);
     }
     
     List<Sucursal> sucursales = sucursalRepository.findByFranquiciaId(franquiciaId);
     return sucursalMapper.toDTOList(sucursales);
 }
 
 @Transactional(readOnly = true)
 public SucursalDTO getSucursalById(Long id) {
     Sucursal sucursal = sucursalRepository.findById(id)
         .orElseThrow(() -> new ResourceNotFoundException("Sucursal no encontrada con id: " + id));
     return sucursalMapper.toDTO(sucursal);
 }
 
 @Transactional
 public SucursalDTO createSucursal(SucursalRequestDTO sucursalDTO) {
     Franquicia franquicia = franquiciaRepository.findById(sucursalDTO.getFranquiciaId())
         .orElseThrow(() -> new ResourceNotFoundException("Franquicia no encontrada con id: " + sucursalDTO.getFranquiciaId()));
     
     if (sucursalRepository.existsByNombreAndFranquiciaId(sucursalDTO.getNombre(), sucursalDTO.getFranquiciaId())) {
         throw new ResourceAlreadyExistsException("Ya existe una sucursal con el nombre: " + sucursalDTO.getNombre() + 
                                                 " para la franquicia con id: " + sucursalDTO.getFranquiciaId());
     }
     
     Sucursal sucursal = sucursalMapper.toEntity(sucursalDTO, franquicia);
     sucursal = sucursalRepository.save(sucursal);
     return sucursalMapper.toDTO(sucursal);
 }
 
 @Transactional
 public SucursalDTO updateSucursal(Long id, SucursalRequestDTO sucursalDTO) {
     Sucursal sucursal = sucursalRepository.findById(id)
         .orElseThrow(() -> new ResourceNotFoundException("Sucursal no encontrada con id: " + id));
     
     // Solo verificamos duplicados si el nombre cambió
     if (!sucursal.getNombre().equals(sucursalDTO.getNombre()) && 
         sucursalRepository.existsByNombreAndFranquiciaId(sucursalDTO.getNombre(), sucursal.getFranquicia().getId())) {
         throw new ResourceAlreadyExistsException("Ya existe una sucursal con el nombre: " + sucursalDTO.getNombre() + 
                                                 " para esta franquicia");
     }
     
     sucursalMapper.updateEntity(sucursal, sucursalDTO);
     sucursal = sucursalRepository.save(sucursal);
     return sucursalMapper.toDTO(sucursal);
 }
 
 @Transactional
 public void deleteSucursal(Long id) {
     if (!sucursalRepository.existsById(id)) {
         throw new ResourceNotFoundException("Sucursal no encontrada con id: " + id);
     }
     sucursalRepository.deleteById(id);
 }
}