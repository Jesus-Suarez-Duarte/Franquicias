package com.franquici.franqui.Dto;



import java.util.List;
import lombok.Data;

@Data
public class SucursalDTO {
 private Long id;
 private String nombre;
 private Long franquiciaId;
 private List<ProductoDTO> productos;
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
public Long getFranquiciaId() {
	return franquiciaId;
}
public void setFranquiciaId(Long franquiciaId) {
	this.franquiciaId = franquiciaId;
}
public List<ProductoDTO> getProductos() {
	return productos;
}
public void setProductos(List<ProductoDTO> productos) {
	this.productos = productos;
}
 
 
}
