package com.empresa.departamentos.exception;

import com.empresa.departamentos.dto.ApiResponse;
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
    public ResponseEntity<ApiResponse<Map<String, String>>> ValidationHandler(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors= new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        ApiResponse<Map<String, String>> response =
                new ApiResponse<>(false, "Errores de validación", errors);

        return ResponseEntity.badRequest().body(response);
    }

    //Rutas no encontradas
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleRouteNotFound(
            NoResourceFoundException ex) {

        ApiResponse<String> response =
                new ApiResponse<>(false, "Recurso no encontrado", null);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    //Departamento por id no encontrado
    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleNotFound(
            DepartmentNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(false, ex.getMessage(), null));
    }
}
