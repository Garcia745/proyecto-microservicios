package com.empresa.empleados.exception;

public class EmpleadoNotFoundException extends RuntimeException {
    public EmpleadoNotFoundException(String mensaje) {
        super(mensaje);
    }
}
