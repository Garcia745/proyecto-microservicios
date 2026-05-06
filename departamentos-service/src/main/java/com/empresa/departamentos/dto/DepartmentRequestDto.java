package com.empresa.departamentos.dto;
import lombok.*;
import jakarta.validation.constraints.NotBlank;

@Setter @Getter
public class DepartmentRequestDto {


    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "La descripcion es obligatoria")
    private String descripcion;
}
