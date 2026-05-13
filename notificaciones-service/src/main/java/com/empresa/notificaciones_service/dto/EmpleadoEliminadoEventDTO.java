package com.empresa.notificaciones_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoEliminadoEventDTO {
    private String id;
    private String nombre;
    private String email;
}
