package com.empresa.notificaciones_service.messaging;

import com.empresa.notificaciones_service.dto.EmpleadoCreadoEventDTO;
import com.empresa.notificaciones_service.dto.EmpleadoEliminadoEventDTO;
import com.empresa.notificaciones_service.model.Notificacion;
import com.empresa.notificaciones_service.model.TipoNotificacion;
import com.empresa.notificaciones_service.service.NotificacionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Listener encargado de consumir eventos provenientes de RabbitMQ.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class NotificacionEventListener {

    private final NotificacionService service;

    /**
     * Escucha eventos de empleado creado.
     */
    @RabbitListener(queues = "notificaciones-creado-queue")
    public void recibirEventoCreado(EmpleadoCreadoEventDTO evento) {
        try {
            log.info("===== EVENTO DE CREACIÓN RECIBIDO =====");
            log.info("ID: {}, Nombre: {}, Email: {}", evento.getId(), evento.getNombre(), evento.getEmail());

            Notificacion notificacion = new Notificacion();

            notificacion.setEmpleadoId(evento.getId());
            notificacion.setDestinatario(evento.getEmail());
            notificacion.setTipo(TipoNotificacion.BIENVENIDA);

            service.procesarYGuardar(notificacion);
            log.info("===== NOTIFICACIÓN DE CREACIÓN GUARDADA EXITOSAMENTE =====");
        } catch (Exception e) {
            log.error("Error procesando evento de creación: {}", e.getMessage(), e);
        }
    }

    /**
     * Escucha eventos de empleado eliminado.
     */
    @RabbitListener(queues = "notificaciones-eliminado-queue")
    public void recibirEventoEliminado(EmpleadoEliminadoEventDTO evento) {
        try {
            log.info("===== EVENTO DE ELIMINACIÓN RECIBIDO =====");
            log.info("ID: {}, Nombre: {}, Email: {}", evento.getId(), evento.getNombre(), evento.getEmail());

            Notificacion notificacion = new Notificacion();

            notificacion.setEmpleadoId(evento.getId());
            notificacion.setDestinatario(evento.getEmail());
            notificacion.setTipo(TipoNotificacion.DESVINCULACION);
            notificacion.setMensaje("El empleado " + evento.getNombre() + " fue eliminado del sistema");
            notificacion.setFechaEnvio(LocalDateTime.now());

            service.procesarYGuardar(notificacion);
            log.info("===== NOTIFICACIÓN DE ELIMINACIÓN GUARDADA EXITOSAMENTE =====");
        } catch (Exception e) {
            log.error("Error procesando evento de eliminación: {}", e.getMessage(), e);
        }
    }
}