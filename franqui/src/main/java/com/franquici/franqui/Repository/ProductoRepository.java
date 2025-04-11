package com.franquici.franqui.Repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.franquici.franqui.Entity.Producto;
import com.franquici.franqui.Entity.Sucursal;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
 List<Producto> findBySucursalId(Long sucursalId);
 Optional<Producto> findByNombreAndSucursal(String nombre, Sucursal sucursal);
 boolean existsByNombreAndSucursalId(String nombre, Long sucursalId);
 
 @Query("SELECT p FROM Producto p WHERE p.sucursal.id IN " +
        "(SELECT s.id FROM Sucursal s WHERE s.franquicia.id = :franquiciaId) " +
        "AND p.stock = (SELECT MAX(p2.stock) FROM Producto p2 WHERE p2.sucursal = p.sucursal)")
 List<Producto> findProductoConMayorStockPorSucursal(@Param("franquiciaId") Long franquiciaId);
}
