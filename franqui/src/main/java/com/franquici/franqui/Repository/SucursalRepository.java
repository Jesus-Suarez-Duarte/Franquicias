package com.franquici.franqui.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.franquici.franqui.Entity.Franquicia;
import com.franquici.franqui.Entity.Sucursal;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Long> {
 List<Sucursal> findByFranquiciaId(Long franquiciaId);
 Optional<Sucursal> findByNombreAndFranquicia(String nombre, Franquicia franquicia);
 boolean existsByNombreAndFranquiciaId(String nombre, Long franquiciaId);
}