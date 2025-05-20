package com.example.employee.repsitory;

import com.example.employee.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.*;

public interface EmployeeRepository extends MongoRepository<Employee, String> {
    Optional<Employee> findByEmail(String email);
}
