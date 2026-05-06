@Slf4j
@Service
public class NotificacionService {

    private final NotificacionRepository repository;

    public NotificacionService(NotificacionRepository repository) {
        this.repository = repository;
    }

    // Este método centraliza la creación del mensaje y el log requerido
    public void procesarYGuardar(Notificacion notificacion) {
        // 1. Definir el mensaje según el tipo (Requisito del reto)
        String mensajeFinal;
        if ("BIENVENIDA".equals(notificacion.getTipo().toString())) {
            mensajeFinal = "Bienvenido al equipo de trabajo.";
        } else {
            mensajeFinal = "Su cuenta ha sido desactivada del sistema.";
        }

        // 2. Actualizar el objeto con el mensaje redactado
        notificacion.setMensaje(mensajeFinal);
        notificacion.setFechaEnvio(java.time.LocalDateTime.now());

        // 3. Guardar en MongoDB
        repository.save(notificacion);

        // 4. EL LOG ESTRUCTURADO (CRÍTICO PARA EL RETO)
        // [NOTIFICACIÓN] Tipo: BIENVENIDA | Para: juan@empresa.com | Mensaje: "Bienvenido Juan..."
        log.info("[NOTIFICACIÓN] Tipo: {} | Para: {} | Mensaje: \"{}\"",
                notificacion.getTipo(),
                notificacion.getDestinatario(),
                notificacion.getMensaje());
    }

    public List<Notificacion> listarNotificaciones() {
        return repository.findAll();
    }

    public List<Notificacion> listarNotificacionesPorEmpleado(String empleadoId) {
        return repository.findByEmpleadoId(empleadoId);
    }
}