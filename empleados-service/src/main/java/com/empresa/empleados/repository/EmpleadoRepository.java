package com.empresa.empleados.repository;
import com.empresa.empleados.model.Empleado;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface EmpleadoRepository extends MongoRepository<Empleado,String>{
}