package com.franquici.franqui.Dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StockUpdateRequestDTO {
 @NotNull(message = "El nuevo stock es requerido")
 @Min(value = 0, message = "El stock no puede ser negativo")
 private Integer nuevoStock;
}