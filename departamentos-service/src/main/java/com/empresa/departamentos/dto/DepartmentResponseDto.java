package com.empresa.departamentos.dto;
import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor

public class DepartmentResponseDto {
    private String id;
    private String nombre;
    private String descripcion;
}
