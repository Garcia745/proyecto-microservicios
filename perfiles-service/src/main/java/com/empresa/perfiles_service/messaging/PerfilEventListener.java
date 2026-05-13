package com.empresa.perfiles_service.messaging;

import com.empresa.perfiles_service.dto.EmpleadoEventDTO;
import com.empresa.perfiles_service.service.PerfilService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PerfilEventListener {

    private final PerfilService perfilService;

    public PerfilEventListener(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    /**
     * Evento: empleado creado
     */
    @RabbitListener(queues = "perfiles-creado-queue")
    public void procesarEmpleadoCreado(EmpleadoEventDTO evento) {
        try {
            log.info("===== EVENTO DE CREACIÓN DE EMPLEADO RECIBIDO =====");
            log.info("ID: {}, Nombre: {}, Email: {}", evento.getId(), evento.getNombre(), evento.getEmail());

            perfilService.crearPerfilPorDefecto(
                    evento.getId(),
                    evento.getNombre(),
                    evento.getEmail()
            );

            log.info("===== PERFIL CREADO EXITOSAMENTE =====");
        } catch (Exception e) {
            log.error("Error procesando evento de creación de empleado: {}", e.getMessage(), e);
        }
    }

    /**
     * Evento: empleado eliminado
     */
    @RabbitListener(queues = "perfiles-eliminado-queue")
    public void procesarEmpleadoEliminado(EmpleadoEventDTO evento) {
        try {
            log.info("===== EVENTO DE ELIMINACIÓN DE EMPLEADO RECIBIDO =====");
            log.info("ID: {}, Nombre: {}, Email: {}", evento.getId(), evento.getNombre(), evento.getEmail());

            perfilService.desactivarPerfil(evento.getId());

            log.info("===== PERFIL DESACTIVADO EXITOSAMENTE =====");
        } catch (Exception e) {
            log.error("Error procesando evento de eliminación de empleado: {}", e.getMessage(), e);
        }
    }
}

