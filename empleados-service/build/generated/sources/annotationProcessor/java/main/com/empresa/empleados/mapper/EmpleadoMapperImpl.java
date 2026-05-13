package com.empresa.empleados.mapper;

import com.empresa.empleados.dto.EmpleadoCreadoEventDTO;
import com.empresa.empleados.dto.EmpleadoEliminadoEventDTO;
import com.empresa.empleados.dto.EmpleadoRequestDTO;
import com.empresa.empleados.dto.EmpleadoResponseDTO;
import com.empresa.empleados.model.Empleado;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-12T23:06:12-0500",
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

    @Override
    public EmpleadoCreadoEventDTO toEvent(Empleado empleado) {
        if ( empleado == null ) {
            return null;
        }

        EmpleadoCreadoEventDTO empleadoCreadoEventDTO = new EmpleadoCreadoEventDTO();

        empleadoCreadoEventDTO.setId( empleado.getId() );
        empleadoCreadoEventDTO.setNombre( empleado.getNombre() );
        empleadoCreadoEventDTO.setEmail( empleado.getEmail() );
        empleadoCreadoEventDTO.setFechaIngreso( empleado.getFechaIngreso() );

        return empleadoCreadoEventDTO;
    }

    @Override
    public EmpleadoEliminadoEventDTO toEliminadoEvent(Empleado empleado) {
        if ( empleado == null ) {
            return null;
        }

        EmpleadoEliminadoEventDTO empleadoEliminadoEventDTO = new EmpleadoEliminadoEventDTO();

        empleadoEliminadoEventDTO.setId( empleado.getId() );
        empleadoEliminadoEventDTO.setNombre( empleado.getNombre() );
        empleadoEliminadoEventDTO.setEmail( empleado.getEmail() );

        return empleadoEliminadoEventDTO;
    }
}
