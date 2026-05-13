package com.empresa.notificaciones_service.repository;
import com.empresa.notificaciones_service.model.Notificacion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificacionRepository extends MongoRepository<Notificacion, String> {
    List<Notificacion> findByEmpleadoId(String empleadoId);
}
