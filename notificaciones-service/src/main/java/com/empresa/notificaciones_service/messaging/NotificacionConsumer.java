import com.empresa.notificaciones_service.dto.EmpleadoEventDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor // Lombok para el constructor
public class NotificacionConsumer {

    private final NotificacionService service;

    @RabbitListener(queues = "notificaciones-queue")
    public void recibirEventoCreado(EmpleadoEventDTO evento) {
        // Le enviamos los datos crudos al servicio para que él redacte el mensaje
        service.crearYProcesarNotificacion(
                evento.getId(),
                evento.getNombre(),
                evento.getEmail(),
                "CREADO"
        );
    }

    // Aquí podrías tener otro @RabbitListener para la eliminación
}