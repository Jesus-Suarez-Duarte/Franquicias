package com.franquici.franqui.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SucursalRequestDTO {
 @NotBlank(message = "El nombre de la sucursal no puede estar vacío")
 @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
 private String nombre;
 
 @NotNull(message = "El ID de la franquicia es requerido")
 private Long franquiciaId;

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
 
 
}