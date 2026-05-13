package com.empresa.perfiles_service.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${rabbitmq.exchange}")
    private String exchangeName;

    @Value("${rabbitmq.routingkey.creado}")
    private String routingKeyCreado;

    @Value("${rabbitmq.routingkey.eliminado}")
    private String routingKeyEliminado;

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }

    /**
     * Cola para empleado creado
     */
    @Bean
    public Queue perfilesCreadoQueue() {
        return new Queue("perfiles-creado-queue", true);
    }

    /**
     * Cola para empleado eliminado
     */
    @Bean
    public Queue perfilesEliminadoQueue() {
        return new Queue("perfiles-eliminado-queue", true);
    }

    /**
     * empleado.creado -> perfiles-creado-queue
     */
    @Bean
    public Binding bindingEmpleadoCreado(
            Queue perfilesCreadoQueue,
            TopicExchange exchange) {

        return BindingBuilder
                .bind(perfilesCreadoQueue)
                .to(exchange)
                .with(routingKeyCreado);
    }

    /**
     * empleado.eliminado -> perfiles-eliminado-queue
     */
    @Bean
    public Binding bindingEmpleadoEliminado(
            Queue perfilesEliminadoQueue,
            TopicExchange exchange) {

        return BindingBuilder
                .bind(perfilesEliminadoQueue)
                .to(exchange)
                .with(routingKeyEliminado);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}