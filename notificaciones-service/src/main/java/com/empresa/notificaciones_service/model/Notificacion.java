package com.empresa.notificaciones_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "notificaciones")
public class Notificacion {

    @Id
    private String id;
    private TipoNotificacion tipo;
    private String destinatario;
    private String mensaje;
    private LocalDateTime fechaEnvio;
    private String empleadoId;
}
