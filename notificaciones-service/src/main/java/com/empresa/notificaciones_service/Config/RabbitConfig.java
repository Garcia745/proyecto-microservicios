package com.empresa.notificaciones_service.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de RabbitMQ para el microservicio de NOTIFICACIONES.
 * Define la infraestructura para que este servicio sea puramente reactivo.
 */
@Configuration
public class RabbitConfig {

    @Value("${rabbitmq.exchange}")
    private String exchangeName;

    @Value("${rabbitmq.queue.notificaciones}")
    private String queueName;

    @Value("${rabbitmq.routingkey.creado}")
    private String routingKeyCreado;

    @Value("${rabbitmq.routingkey.eliminado}")
    private String routingKeyEliminado;

    // 🔹 El Exchange debe llamarse IGUAL que en los otros servicios
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }

    // 🔹 Esta es la cola PROPIA de este servicio
    @Bean
    public Queue notificacionesQueue() {
        return new Queue(queueName, true);
    }

    // 🔹 Vincula la cola de notificaciones al evento creado
    @Bean
    public Binding bindingNotificacionCreado(Queue notificacionesQueue, TopicExchange exchange) {
        return BindingBuilder
                .bind(notificacionesQueue)
                .to(exchange)
                .with(routingKeyCreado);
    }

    // 🔹 Vincula la cola de notificaciones al evento eliminado
    @Bean
    public Binding bindingNotificacionEliminado(Queue notificacionesQueue, TopicExchange exchange) {
        return BindingBuilder
                .bind(notificacionesQueue)
                .to(exchange)
                .with(routingKeyEliminado);
    }

    /**
     * 🔹 CRÍTICO: Convertidor de mensajes a JSON.
     * Permite que el @RabbitListener convierta automáticamente el JSON del broker
     * al objeto EmpleadoEventDTO de Java.
     */
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}