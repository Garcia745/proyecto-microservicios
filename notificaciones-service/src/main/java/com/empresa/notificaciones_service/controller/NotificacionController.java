package com.empresa.notificaciones_service.controller;

import com.empresa.notificaciones_service.dto.ApiResponse;
import com.empresa.notificaciones_service.model.Notificacion;
import com.empresa.notificaciones_service.service.NotificacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {

    private final NotificacionService service;

    public NotificacionController(NotificacionService service) {
        this.service = service;
    }

    // 🔹 Obtener todas las notificaciones
    @GetMapping
    public ResponseEntity<ApiResponse<List<Notificacion>>> listarNotificaciones() {

        List<Notificacion> notificaciones = service.listarNotificaciones();

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Notificaciones encontradas", notificaciones)
        );
    }

    // 🔹 Obtener notificaciones por empleadoId
    @GetMapping("/{empleadoId}")
    public ResponseEntity<ApiResponse<List<Notificacion>>> obtenerNotificacionesPorEmpleado(@PathVariable String empleadoId) {

        List<Notificacion> notificaciones = service.listarNotificacionesPorEmpleado(empleadoId);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Notificaciones encontradas", notificaciones)
        );
    }
}
