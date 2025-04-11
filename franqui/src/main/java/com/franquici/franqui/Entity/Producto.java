package com.franquici.franqui.Entity;

import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "productos", uniqueConstraints = {
 @UniqueConstraint(columnNames = {"nombre", "sucursal_id"}, name = "uk_producto_sucursal")
})
@Data
public class Producto {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 
 @Column(nullable = false)
 private String nombre;
 
 @Column(nullable = false)
 private Integer stock;
 
 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "sucursal_id", nullable = false)
 private Sucursal sucursal;
 
 @Column(name = "fecha_creacion")
 @Temporal(TemporalType.TIMESTAMP)
 private Date fechaCreacion;
 
 @PrePersist
 protected void onCreate() {
     fechaCreacion = new Date();
     if (stock == null) {
         stock = 0;
     }
 }

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}

public Integer getStock() {
	return stock;
}

public void setStock(Integer stock) {
	this.stock = stock;
}

public Sucursal getSucursal() {
	return sucursal;
}

public void setSucursal(Sucursal sucursal) {
	this.sucursal = sucursal;
}

public Date getFechaCreacion() {
	return fechaCreacion;
}

public void setFechaCreacion(Date fechaCreacion) {
	this.fechaCreacion = fechaCreacion;
}
 
}