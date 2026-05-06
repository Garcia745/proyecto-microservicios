package com.empresa.departamentos.repository;

import com.empresa.departamentos.model.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentRepository extends MongoRepository<Department, String> {
}