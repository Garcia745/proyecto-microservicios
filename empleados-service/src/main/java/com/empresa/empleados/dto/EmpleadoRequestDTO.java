package com.empresa.empleados.dto;
import lombok.*;
import jakarta.validation.constraints.NotBlank;
import java.util.Date;

@Setter @Getter
public class EmpleadoRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El cargo es obligatorio")
    private String cargo;

    @NotBlank(message = "El email es obligatorio")
    private String email;

    @NotBlank(message = "El departamento es obligatorio")
    private String departamentoId;

}
