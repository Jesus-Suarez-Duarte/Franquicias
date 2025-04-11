package com.franquici.franqui.Entity;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "sucursales", uniqueConstraints = {
 @UniqueConstraint(columnNames = {"nombre", "franquicia_id"}, name = "uk_sucursal_franquicia")
})
@Data
public class Sucursal {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 
 @Column(nullable = false)
 private String nombre;
 
 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "franquicia_id", nullable = false)
 private Franquicia franquicia;
 
 @Column(name = "fecha_creacion")
 @Temporal(TemporalType.TIMESTAMP)
 private Date fechaCreacion;
 
 @OneToMany(mappedBy = "sucursal", cascade = CascadeType.ALL, orphanRemoval =
 true) private List<Producto> productos = new ArrayList<>();
 
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

public Franquicia getFranquicia() {
	return franquicia;
}

public void setFranquicia(Franquicia franquicia) {
	this.franquicia = franquicia;
}

public Date getFechaCreacion() {
	return fechaCreacion;
}

public void setFechaCreacion(Date fechaCreacion) {
	this.fechaCreacion = fechaCreacion;
}

public List<Producto> getProductos() {
	return productos;
}

public void setProductos(List<Producto> productos) {
	this.productos = productos;
}
 
}