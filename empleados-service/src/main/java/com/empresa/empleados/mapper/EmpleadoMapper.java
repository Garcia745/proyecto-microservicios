package com.empresa.empleados.mapper;

import com.empresa.empleados.dto.EmpleadoRequestDTO;
import com.empresa.empleados.dto.EmpleadoResponseDTO;
import com.empresa.empleados.model.Empleado;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmpleadoMapper {

        Empleado toEntity(EmpleadoRequestDTO dto);
        EmpleadoResponseDTO toResponse(Empleado empleado);
}
