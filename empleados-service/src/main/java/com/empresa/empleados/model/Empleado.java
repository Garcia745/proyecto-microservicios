package com.empresa.empleados.model;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "employes")
public class Empleado {

    @Id
    private String id;
    private String nombre;
    private String cargo;
    private String email;
    private String departamentoId;
    private LocalDate fechaIngreso;
}