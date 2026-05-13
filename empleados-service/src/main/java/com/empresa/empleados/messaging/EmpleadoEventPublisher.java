package com.empresa.empleados.messaging;

import com.empresa.empleados.dto.EmpleadoCreadoEventDTO;
import com.empresa.empleados.dto.EmpleadoEliminadoEventDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmpleadoEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    private static final String EXCHANGE = "empleados.exchange";
    private static final String ROUTING_KEY_CREADO = "empleado.creado";
    private static final String ROUTING_KEY_ELIMINADO = "empleado.eliminado";

    public void publicarCreado(EmpleadoCreadoEventDTO event) {

        log.info("===== PUBLICANDO EVENTO DE CREACIÓN =====");
        log.info("Exchange: {}, Routing Key: {}", EXCHANGE, ROUTING_KEY_CREADO);
        log.info("Datos: ID={}, Nombre={}, Email={}", event.getId(), event.getNombre(), event.getEmail());
        rabbitTemplate.convertAndSend(
                EXCHANGE,
                ROUTING_KEY_CREADO,
                event
        );
        log.info("===== EVENTO DE CREACIÓN ENVIADO EXITOSAMENTE =====");
    }

    public void publicarEliminado(EmpleadoEliminadoEventDTO  event) {
        log.info("===== PUBLICANDO EVENTO DE ELIMINACIÓN =====");
        log.info("Exchange: {}, Routing Key: {}", EXCHANGE, ROUTING_KEY_ELIMINADO);
        log.info("Datos: ID={}, Nombre={}, Email={}", event.getId(), event.getNombre(), event.getEmail());
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY_ELIMINADO, event);
        log.info("===== EVENTO DE ELIMINACIÓN ENVIADO EXITOSAMENTE =====");
    }
}
