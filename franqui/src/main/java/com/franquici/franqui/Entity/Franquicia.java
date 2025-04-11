package com.franquici.franqui.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "franquicias")
@Data
public class Franquicia {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 
 @Column(nullable = false, unique = true)
 private String nombre;
 
 @Column(name = "fecha_creacion")
 @Temporal(TemporalType.TIMESTAMP)
 private Date fechaCreacion;
 
 @OneToMany(mappedBy = "franquicia", cascade = CascadeType.ALL, orphanRemoval = true)
 private List<Sucursal> sucursales = new ArrayList<>();
 
 @PrePersist
 protected void onCreate() {
     fechaCreacion = new Date();
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

public Date getFechaCreacion() {
	return fechaCreacion;
}

public void setFechaCreacion(Date fechaCreacion) {
	this.fechaCreacion = fechaCreacion;
}

public List<Sucursal> getSucursales() {
	return sucursales;
}

public void setSucursales(List<Sucursal> sucursales) {
	this.sucursales = sucursales;
}
 
 
}