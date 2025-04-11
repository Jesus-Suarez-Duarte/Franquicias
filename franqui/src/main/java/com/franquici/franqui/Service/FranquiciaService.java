package com.franquici.franqui.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.franquici.franqui.Dto.FranquiciaDTO;
import com.franquici.franqui.Dto.FranquiciaRequestDTO;
import com.franquici.franqui.exception.ResourceAlreadyExistsException;
import com.franquici.franqui.exception.ResourceNotFoundException;
import com.franquici.franqui.mapper.FranquiciaMapper;
import com.franquici.franqui.Entity.Franquicia;
import com.franquici.franqui.Repository.FranquiciaRepository;

@Service
public class FranquiciaService {
 
 @Autowired
 private FranquiciaRepository franquiciaRepository;
 
 @Autowired
 private FranquiciaMapper franquiciaMapper;
 
 @Transactional(readOnly = true)
 public List<FranquiciaDTO> getAllFranquicias() {
     List<Franquicia> franquicias = franquiciaRepository.findAll();
     return franquiciaMapper.toDTOList(franquicias);
 }
 
 @Transactional(readOnly = true)
 public FranquiciaDTO getFranquiciaById(Long id) {
     Franquicia franquicia = franquiciaRepository.findById(id)
         .orElseThrow(() -> new ResourceNotFoundException("Franquicia no encontrada con id: " + id));
     return franquiciaMapper.toDTO(franquicia);
 }
 
 @Transactional
 public FranquiciaDTO createFranquicia(FranquiciaRequestDTO franquiciaDTO) {
     if (franquiciaRepository.existsByNombre(franquiciaDTO.getNombre())) {
         throw new ResourceAlreadyExistsException("Ya existe una franquicia con el nombre: " + franquiciaDTO.getNombre());
     }
     
     Franquicia franquicia = franquiciaMapper.toEntity(franquiciaDTO);
     franquicia = franquiciaRepository.save(franquicia);
     return franquiciaMapper.toDTO(franquicia);
 }
 
 @Transactional
 public FranquiciaDTO updateFranquicia(Long id, FranquiciaRequestDTO franquiciaDTO) {
     Franquicia franquicia = franquiciaRepository.findById(id)
         .orElseThrow(() -> new ResourceNotFoundException("Franquicia no encontrada con id: " + id));
     
     // Verificar si el nuevo nombre ya existe en otra franquicia
     if (!franquicia.getNombre().equals(franquiciaDTO.getNombre()) && 
         franquiciaRepository.existsByNombre(franquiciaDTO.getNombre())) {
         throw new ResourceAlreadyExistsException("Ya existe una franquicia con el nombre: " + franquiciaDTO.getNombre());
     }
     
     franquiciaMapper.updateEntity(franquicia, franquiciaDTO);
     franquicia = franquiciaRepository.save(franquicia);
     return franquiciaMapper.toDTO(franquicia);
 }
 
 @Transactional
 public void deleteFranquicia(Long id) {
     if (!franquiciaRepository.existsById(id)) {
         throw new ResourceNotFoundException("Franquicia no encontrada con id: " + id);
     }
     franquiciaRepository.deleteById(id);
 }
}