package com.empresa.empleados.exception;
import com.empresa.empleados.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //Errores de validacion en campos
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> manejarValidaciones(
            MethodArgumentNotValidException ex) {

        Map<String, String> errores = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errores.put(error.getField(), error.getDefaultMessage())
        );

        ApiResponse<Map<String, String>> response =
                new ApiResponse<>(false, "Errores de validación", errores);

        return ResponseEntity.badRequest().body(response);
    }

    //Rutas no encontradas
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiResponse<String>> manejarRutaNoEncontrada(
            NoResourceFoundException ex) {

        ApiResponse<String> response =
                new ApiResponse<>(false, "Recurso no encontrado", null);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    //Empleado por id no encontrado
    @ExceptionHandler(EmpleadoNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> manejarNoEncontrado(
            EmpleadoNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(false, ex.getMessage(), null));
    }

    //Departamento por id no encontrado
    @ExceptionHandler(DepartamentoNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> manejarDepartamentoNoEncontrado(
            DepartamentoNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(false, ex.getMessage(), null));
    }
}
