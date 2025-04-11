package com.franquici.franqui.Dto;


import lombok.Data;

@Data
public class ProductoDTO {
 private Long id;
 private String nombre;
 private Integer stock;
 private Long sucursalId;
 
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
public Long getSucursalId() {
	return sucursalId;
}
public void setSucursalId(Long sucursalId) {
	this.sucursalId = sucursalId;
}
 
 
}