package com.empresa.notificaciones_service.mapper;

import com.empresa.notificaciones_service.dto.NotificacionResponseDTO;
import com.empresa.notificaciones_service.model.Notificacion;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-21T17:09:50-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.4.jar, environment: Java 17.0.17 (Eclipse Adoptium)"
)
@Component
public class NotificacionMapperImpl implements NotificacionMapper {

    @Override
    public NotificacionResponseDTO toResponseDTO(Notificacion notificacion) {
        if ( notificacion == null ) {
            return null;
        }

        NotificacionResponseDTO notificacionResponseDTO = new NotificacionResponseDTO();

        return notificacionResponseDTO;
    }

    @Override
    public List<NotificacionResponseDTO> toResponseDTOList(List<Notificacion> notificaciones) {
        if ( notificaciones == null ) {
            return null;
        }

        List<NotificacionResponseDTO> list = new ArrayList<NotificacionResponseDTO>( notificaciones.size() );
        for ( Notificacion notificacion : notificaciones ) {
            list.add( toResponseDTO( notificacion ) );
        }

        return list;
    }
}
