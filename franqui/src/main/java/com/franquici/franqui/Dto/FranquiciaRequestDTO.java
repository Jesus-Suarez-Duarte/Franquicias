package com.franquici.franqui.Dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class FranquiciaRequestDTO {
 @NotBlank(message = "El nombre de la franquicia no puede estar vacío")
 @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
 private String nombre;

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}
 
 
}