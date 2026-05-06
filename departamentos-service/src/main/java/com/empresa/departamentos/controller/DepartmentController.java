package com.empresa.departamentos.controller;

import com.empresa.departamentos.dto.ApiResponse;
import com.empresa.departamentos.dto.DepartmentRequestDto;
import com.empresa.departamentos.dto.DepartmentResponseDto;
import com.empresa.departamentos.mapper.DepartmentMapper;
import com.empresa.departamentos.model.Department;
import com.empresa.departamentos.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/departments") // Se recomienda agregar /api/v1 para seguir estándares
@Tag(name = "Department Controller", description = "Endpoints para la gestión de departamentos en MongoDB")
public class DepartmentController {

    private final DepartmentService service;
    private final DepartmentMapper mapper;

    public DepartmentController(DepartmentService service, DepartmentMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    @Operation(summary = "Registrar un departamento", description = "Crea un nuevo departamento en la base de datos MongoDB.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Departamento creado exitosamente")
    public ResponseEntity<ApiResponse<DepartmentResponseDto>> registerDepartment(@RequestBody @Valid DepartmentRequestDto request) {
        Department department = mapper.toEntity(request);
        Department guardado = service.registerDepartment(department);
        DepartmentResponseDto response = mapper.toResponse(guardado);

        ApiResponse<DepartmentResponseDto> responseFinal = new ApiResponse<>(
                true, "Departamento creado exitosamente", response
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(responseFinal);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar por ID", description = "Busca un departamento específico usando su ID único de MongoDB.")
    public ResponseEntity<ApiResponse<DepartmentResponseDto>> getDepartmentById(@PathVariable String id) {
        Department department = service.getDepartmentById(id);
        DepartmentResponseDto departmentS = mapper.toResponse(department);

        ApiResponse<DepartmentResponseDto> responseFinal = new ApiResponse<>(
                true, "Departamento encontrado", departmentS
        );
        return ResponseEntity.ok(responseFinal);
    }

    @GetMapping
    @Operation(summary = "Listar todos", description = "Retorna una lista con todos los departamentos registrados.")
    public ResponseEntity<ApiResponse<List<DepartmentResponseDto>>> getAllDepartments() {
        List<Department> departments = service.getAllDepartments();
        List<DepartmentResponseDto> departmentsDto = mapper.toResponseList(departments);

        ApiResponse<List<DepartmentResponseDto>> responseFinal =
                new ApiResponse<>(true, "Departamentos encontrados", departmentsDto);

        return ResponseEntity.ok(responseFinal);
    }

    @DeleteMapping
    @Operation(
            summary = "Eliminar todos los departamentos",
            description = "Borra permanentemente todos los registros. Retorna 204 si la operación fue exitosa."
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "204",
            description = "Departamentos eliminados, sin contenido para mostrar"
    )
    public ResponseEntity<Void> deleteDepartments() {
        service.deleteAllDepartments();

        // .noContent() genera el estatus 204
        // .build() construye la respuesta sin cuerpo (body)
        return ResponseEntity.noContent().build();
    }
}