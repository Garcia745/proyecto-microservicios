package com.empresa.notificaciones_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoCreadoEventDTO {

    private String id;
    private String nombre;
    private String email;
    private LocalDate fechaIngreso;
}
