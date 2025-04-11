package com.franquici.franqui.Dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductoRequestDTO {
 @NotBlank(message = "El nombre del producto no puede estar vacío")
 @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
 private String nombre;
 
 @NotNull(message = "El stock es requerido")
 @Min(value = 0, message = "El stock no puede ser negativo")
 private Integer stock;
 
 @NotNull(message = "El ID de la sucursal es requerido")
 private Long sucursalId;

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
