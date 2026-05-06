package com.empresa.empleados.exception;

public class DepartamentoNotFoundException extends RuntimeException {
    public DepartamentoNotFoundException(String message) {
        super(message);
    }
}
