package com.franquici.franqui.Dto;

import java.util.List;
import lombok.Data;

@Data
public class FranquiciaDTO {
 private Long id;
 private String nombre;
 private List<SucursalDTO> sucursales;
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
public List<SucursalDTO> getSucursales() {
	return sucursales;
}
public void setSucursales(List<SucursalDTO> sucursales) {
	this.sucursales = sucursales;
}
 
 
}