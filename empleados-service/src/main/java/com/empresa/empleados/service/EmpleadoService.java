package com.empresa.empleados.service;

import com.empresa.empleados.dto.ApiResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import com.empresa.empleados.dto.DepartamentoDTO;
import com.empresa.empleados.dto.EmpleadoResponseDTO;
import com.empresa.empleados.exception.DepartamentoNotFoundException;
import com.empresa.empleados.exception.EmpleadoNotFoundException;
import com.empresa.empleados.mapper.EmpleadoMapper;
import com.empresa.empleados.model.Empleado;
import com.empresa.empleados.repository.EmpleadoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;

/**
 * Servicio encargado de la lógica de negocio relacionada con empleados.
 *
 * Funcionalidades principales:
 * - Registrar empleados
 * - Obtener empleados con información del departamento (microservicio externo)
 * - Eliminar empleados
 * - Mapear entidades a DTOs enriquecidos
 */
@Service
public class EmpleadoService {

    private static final Logger logger = LoggerFactory.getLogger(EmpleadoService.class);

    @Value("${departamentos.url}")
    private String departamentosUrl;

    @Autowired
    private EmpleadoRepository repository;

    @Autowired
    private EmpleadoMapper mapper;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Obtiene el departamento usando Circuit Breaker.
     * Si falla o el circuito está abierto, salta al método fallback.
     */
    @CircuitBreaker(name = "departamentosCB", fallbackMethod = "fallbackObtenerDepartamento")
    private DepartamentoDTO obtenerDepartamento(String departamentoId) {
        ResponseEntity<ApiResponse<DepartamentoDTO>> resp =
                restTemplate.exchange(
                        departamentosUrl + "/departments/{id}",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<ApiResponse<DepartamentoDTO>>() {},
                        departamentoId
                );

        ApiResponse<DepartamentoDTO> body = resp.getBody();
        if (body != null && body.getData() != null) {
            return body.getData();
        }
        throw new DepartamentoNotFoundException("Departamento no encontrado");
    }

    /**
     * Método Fallback: Se ejecuta si el microservicio de departamentos está caído
     * o si el Circuit Breaker decide abrir el circuito.
     */
    public DepartamentoDTO fallbackObtenerDepartamento(String departamentoId, Throwable e) {
        logger.error("Fallo al contactar Departamentos (ID: {}). Error: {}", departamentoId, e.getMessage());
        // Devolvemos un objeto "Dummy" o lanzamos una excepción controlada para el reto
        return new DepartamentoDTO(departamentoId, "Servicio No Disponible", "Temporalmente fuera de línea");
    }

    public EmpleadoResponseDTO registrarEmpleado(Empleado empleado) {
        empleado.setFechaIngreso(LocalDate.now());

        // Llamada protegida
        DepartamentoDTO departamento = obtenerDepartamento(empleado.getDepartamentoId());

        // Validación lógica del reto: si el depto no existe de verdad (puedes ajustar esta lógica)
        if (departamento.getNombre().equals("Servicio No Disponible")) {
            throw new DepartamentoNotFoundException("No se pudo validar el departamento en este momento.");
        }

        Empleado guardado = repository.save(empleado);
        EmpleadoResponseDTO dto = mapper.toResponse(guardado);
        dto.setDepartamento(departamento);
        return dto;
    }

    public EmpleadoResponseDTO obtenerEmpleado(String id) {
        Empleado empleado = repository.findById(id)
                .orElseThrow(() -> new EmpleadoNotFoundException("Empleado no existe"));

        EmpleadoResponseDTO dto = mapper.toResponse(empleado);
        try {
            dto.setDepartamento(obtenerDepartamento(empleado.getDepartamentoId()));
        } catch (Exception e) {
            dto.setDepartamento(null);
        }
        return dto;
    }

    public EmpleadoResponseDTO eliminarEmpleado(String id) {
        EmpleadoResponseDTO empleado = obtenerEmpleado(id);
        repository.deleteById(id);
        return empleado;
    }
}