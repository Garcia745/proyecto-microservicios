package com.empresa.notificaciones_service.service;

import com.empresa.notificaciones_service.model.Notificacion;
import com.empresa.notificaciones_service.model.TipoNotificacion;
import com.empresa.notificaciones_service.repository.NotificacionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Servicio encargado de gestionar las notificaciones del sistema.
 *
 * Funcionalidades principales:
 * - Procesar eventos recibidos desde RabbitMQ.
 * - Generar el mensaje correspondiente según el tipo de notificación.
 * - Guardar el historial de notificaciones en MongoDB.
 * - Registrar logs estructurados simulando el envío de correos.
 */
@Slf4j
@Service
public class NotificacionService {

    /**
     * Repositorio utilizado para acceder a la colección de notificaciones.
     */
    private final NotificacionRepository repository;

    /**
     * Constructor con inyección de dependencias.
     *
     * @param repository repositorio de notificaciones
     */

    public NotificacionService(NotificacionRepository repository) {
        this.repository = repository;
    }

    /**
     * Procesa y almacena una notificación recibida desde RabbitMQ.
     *
     * Flujo del proceso:
     * 1. Construir el mensaje según el tipo de notificación.
     * 2. Registrar fecha y hora del envío.
     * 3. Guardar la notificación en MongoDB.
     * 4. Generar un log estructurado simulando el envío del correo.
     *
     *
     * @param notificacion objeto de notificación recibido desde el listener
     */
    public void procesarYGuardar(Notificacion notificacion) {

        String mensajeFinal;

        // Validar el tipo de notificación recibido
        if (notificacion.getTipo() == TipoNotificacion.BIENVENIDA) {

            // Mensaje para empleados recién creados
            mensajeFinal =
                    "Bienvenido al equipo de trabajo.";

        } else {

            // Mensaje para empleados desvinculados
            mensajeFinal =
                    "Su cuenta ha sido desactivada del sistema.";
        }

        // Asignar el mensaje generado automáticamente si no viene predefinido
        if (notificacion.getMensaje() == null) {
            notificacion.setMensaje(mensajeFinal);
        }

        // Registrar fecha y hora del envío
        notificacion.setFechaEnvio(LocalDateTime.now());

        /*
         * Guardar la notificación en MongoDB.
         *
         * El empleadoId ya viene desde el evento recibido
         * por RabbitMQ y se conserva para mantener el
         * historial de notificaciones asociado al empleado.
         */
        repository.save(notificacion);

        log.info(
                "[NOTIFICACIÓN] Tipo: {} | Para: {} | Mensaje: \"{}\"",
                notificacion.getTipo(),
                notificacion.getDestinatario(),
                notificacion.getMensaje()
        );
    }

    /**
     * Obtiene el historial completo de notificaciones.
     *
     * @return lista de notificaciones registradas
     */
    public List<Notificacion> listarNotificaciones() {
        return repository.findAll();
    }

    /**
     * Obtiene las notificaciones asociadas a un empleado específico.
     *
     * @param empleadoId identificador del empleado
     * @return lista de notificaciones del empleado
     */
    public List<Notificacion> listarNotificacionesPorEmpleado(String empleadoId) {
        return repository.findByEmpleadoId(empleadoId);
    }
}