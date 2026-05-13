package com.empresa.notificaciones_service.dto;

import com.empresa.notificaciones_service.model.TipoNotificacion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificacionResponseDTO {

    private String id;
    private TipoNotificacion tipo;
    private String destinatario;
    private String mensaje;
    private LocalDateTime fechaEnvio;
    private String empleadoId;
}