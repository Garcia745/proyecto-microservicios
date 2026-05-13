package com.empresa.notificaciones_service.mapper;

import com.empresa.notificaciones_service.dto.NotificacionResponseDTO;
import com.empresa.notificaciones_service.model.Notificacion;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface NotificacionMapper {

    NotificacionResponseDTO toResponseDTO(Notificacion notificacion);

    List<NotificacionResponseDTO> toResponseDTOList(
            List<Notificacion> notificaciones
    );
}