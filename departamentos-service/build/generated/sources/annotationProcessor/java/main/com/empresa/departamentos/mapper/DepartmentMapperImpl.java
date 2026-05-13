package com.empresa.departamentos.mapper;

import com.empresa.departamentos.dto.DepartmentRequestDto;
import com.empresa.departamentos.dto.DepartmentResponseDto;
import com.empresa.departamentos.model.Department;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-12T16:21:27-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.4.jar, environment: Java 17.0.17 (Eclipse Adoptium)"
)
@Component
public class DepartmentMapperImpl implements DepartmentMapper {

    @Override
    public Department toEntity(DepartmentRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Department.DepartmentBuilder department = Department.builder();

        department.nombre( dto.getNombre() );
        department.descripcion( dto.getDescripcion() );

        return department.build();
    }

    @Override
    public DepartmentResponseDto toResponse(Department department) {
        if ( department == null ) {
            return null;
        }

        DepartmentResponseDto departmentResponseDto = new DepartmentResponseDto();

        departmentResponseDto.setId( department.getId() );
        departmentResponseDto.setNombre( department.getNombre() );
        departmentResponseDto.setDescripcion( department.getDescripcion() );

        return departmentResponseDto;
    }

    @Override
    public List<DepartmentResponseDto> toResponseList(List<Department> departments) {
        if ( departments == null ) {
            return null;
        }

        List<DepartmentResponseDto> list = new ArrayList<DepartmentResponseDto>( departments.size() );
        for ( Department department : departments ) {
            list.add( toResponse( department ) );
        }

        return list;
    }
}
