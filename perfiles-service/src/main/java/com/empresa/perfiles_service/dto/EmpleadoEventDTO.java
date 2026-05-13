package com.empresa.perfiles_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * DTO que representa el evento enviado desde el microservicio de empleados.
 * Spring convertirá automáticamente el JSON recibido a este objeto.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoEventDTO {

    private String id;
    private String nombre;
    private String email;
    private String departamentoId;
    private LocalDate fechaIngreso;
}