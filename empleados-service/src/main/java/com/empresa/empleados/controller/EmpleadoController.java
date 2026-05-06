package com.empresa.empleados.controller;

import com.empresa.empleados.dto.*;
import com.empresa.empleados.mapper.EmpleadoMapper;
import com.empresa.empleados.model.Empleado;
import com.empresa.empleados.service.EmpleadoService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    private final EmpleadoService service;
    private final EmpleadoMapper mapper;

    public EmpleadoController(EmpleadoService service, EmpleadoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    // 1️⃣ Registrar empleado
    @PostMapping
    public ResponseEntity<ApiResponse<EmpleadoResponseDTO>> registrar(@RequestBody @Valid EmpleadoRequestDTO request) {
        // 1️⃣ DTO → Entity
        Empleado empleado = mapper.toEntity(request);
        // 2️⃣ Guardar en el service
        EmpleadoResponseDTO response = service.registrarEmpleado(empleado);
        // 4️⃣ Respuesta HTTP
        ApiResponse<EmpleadoResponseDTO> responseFinal = new ApiResponse<>(
                true, "Empleado creado correctamente",response
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(responseFinal);
    }

    // 2️⃣ Consultar empleado por id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EmpleadoResponseDTO>> obtener(@PathVariable String id) {
        EmpleadoResponseDTO empleado = service.obtenerEmpleado(id);
        ApiResponse<EmpleadoResponseDTO> responseFinal= new ApiResponse<>(
                true, "Empleado encontrado", empleado
                );
        return ResponseEntity.ok(responseFinal);
    }

    // 3 Eliminar empleado por id
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable String id) {
        EmpleadoResponseDTO empleado = service.eliminarEmpleado(id);
        ApiResponse<String> responseFinal = new ApiResponse<>(
                true, "El empleado " + empleado.getNombre() + " ha sido eliminado exitosamente",null );
        return ResponseEntity.ok(responseFinal);
    }
}