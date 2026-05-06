package com.empresa.empleados.dto;
import lombok.*;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoResponseDTO {

    private String id;
    private String nombre;
    private String email;
    private LocalDate fechaIngreso;
    private DepartamentoDTO departamento;
}
