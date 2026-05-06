package com.empresa.departamentos.service;
import com.empresa.departamentos.exception.DepartmentNotFoundException;
import com.empresa.departamentos.model.Department;
import com.empresa.departamentos.repository.DepartamentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {

    private final DepartamentRepository repository;

    public DepartmentService(DepartamentRepository repository){
        this.repository=repository;
    }

    public Department registerDepartment(Department department){
        return repository.save(department);
    }
    public Department getDepartmentById(String id){

        //.orElseThrow Verifica si esta vacio o no
        return repository.findById(id)
                .orElseThrow(() ->
                        new DepartmentNotFoundException(
                                "El departamento con id " + id + " no existe"
                        ));
    }
    public List<Department> getAllDepartments(){

        return new ArrayList<>(repository.findAll());
    }
    public void deleteAllDepartments(){
        repository.deleteAll();
    }
}

