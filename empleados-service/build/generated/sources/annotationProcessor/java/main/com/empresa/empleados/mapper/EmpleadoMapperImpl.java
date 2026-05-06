package com.empresa.empleados.mapper;

import com.empresa.empleados.dto.EmpleadoRequestDTO;
import com.empresa.empleados.dto.EmpleadoResponseDTO;
import com.empresa.empleados.model.Empleado;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-04T12:36:47-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-9.3.0.jar, environment: Java 17.0.17 (Eclipse Adoptium)"
)
@Component
public class EmpleadoMapperImpl implements EmpleadoMapper {

    @Override
    public Empleado toEntity(EmpleadoRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Empleado empleado = new Empleado();

        empleado.setNombre( dto.getNombre() );
        empleado.setCargo( dto.getCargo() );
        empleado.setEmail( dto.getEmail() );
        empleado.setDepartamentoId( dto.getDepartamentoId() );

        return empleado;
    }

    @Override
    public EmpleadoResponseDTO toResponse(Empleado empleado) {
        if ( empleado == null ) {
            return null;
        }

        EmpleadoResponseDTO empleadoResponseDTO = new EmpleadoResponseDTO();

        empleadoResponseDTO.setId( empleado.getId() );
        empleadoResponseDTO.setNombre( empleado.getNombre() );
        empleadoResponseDTO.setEmail( empleado.getEmail() );
        empleadoResponseDTO.setFechaIngreso( empleado.getFechaIngreso() );

        return empleadoResponseDTO;
    }
}
