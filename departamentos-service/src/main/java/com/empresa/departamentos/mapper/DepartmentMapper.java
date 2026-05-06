package com.empresa.departamentos.mapper;
import com.empresa.departamentos.dto.DepartmentRequestDto;
import com.empresa.departamentos.dto.DepartmentResponseDto;
import com.empresa.departamentos.model.Department;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    Department toEntity(DepartmentRequestDto dto);
    DepartmentResponseDto toResponse(Department department);
    List<DepartmentResponseDto> toResponseList(List<Department> departments);
}
