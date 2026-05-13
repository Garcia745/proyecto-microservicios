package com.empresa.notificaciones_service.Config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración RabbitMQ del microservicio de NOTIFICACIONES.
 *
 * Este servicio consume dos eventos:
 * - empleado.creado
 * - empleado.eliminado
 *
 * Cada evento tiene su propia cola para separar responsabilidades.
 */
@Configuration
public class RabbitConfig {

    /**
     * Nombre del exchange compartido entre microservicios.
     */
    @Value("${rabbitmq.exchange}")
    private String exchangeName;

    /**
     * Routing key para empleado creado.
     */
    @Value("${rabbitmq.routingkey.creado}")
    private String routingKeyCreado;

    /**
     * Routing key para empleado eliminado.
     */
    @Value("${rabbitmq.routingkey.eliminado}")
    private String routingKeyEliminado;

    /**
     * Exchange principal.
     */
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }

    /**
     * Cola para eventos de creación de empleados.
     */
    @Bean
    public Queue creadoQueue() {
        return new Queue("notificaciones-creado-queue", true);
    }

    /**
     * Cola para eventos de eliminación de empleados.
     */
    @Bean
    public Queue eliminadoQueue() {
        return new Queue("notificaciones-eliminado-queue", true);
    }

    /**
     * Binding:
     * empleado.creado -> notificaciones-creado-queue
     */
    @Bean
    public Binding bindingCreado(
            Queue creadoQueue,
            TopicExchange exchange) {

        return BindingBuilder
                .bind(creadoQueue)
                .to(exchange)
                .with(routingKeyCreado);
    }

    /**
     * Binding:
     * empleado.eliminado -> notificaciones-eliminado-queue
     */
    @Bean
    public Binding bindingEliminado(
            Queue eliminadoQueue,
            TopicExchange exchange) {

        return BindingBuilder
                .bind(eliminadoQueue)
                .to(exchange)
                .with(routingKeyEliminado);
    }

    /**
     * Convertidor JSON.
     *
     * Permite convertir automáticamente mensajes JSON
     * recibidos desde RabbitMQ a objetos Java.
     */
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}