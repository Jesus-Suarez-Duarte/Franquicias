package com.franquici.franqui.Repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.franquici.franqui.Entity.Franquicia;

@Repository
public interface FranquiciaRepository extends JpaRepository<Franquicia, Long> {
 Optional<Franquicia> findByNombre(String nombre);
 boolean existsByNombre(String nombre);
}