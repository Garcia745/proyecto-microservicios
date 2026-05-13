package com.empresa.empleados.model;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Document(collection = "employes")
public class Empleado {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String nombre;
    private String cargo;
    private String email;
    private String departamentoId;
    private LocalDate fechaIngreso;
}