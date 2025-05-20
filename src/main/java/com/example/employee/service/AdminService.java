package com.example.employee.service;

import com.example.employee.exception.ResourceNotFoundException;
import com.example.employee.model.Employee;
import com.example.employee.payload.LoginRequest;
import com.example.employee.payload.RegisterRequest;
import com.example.employee.repsitory.EmployeeRepository;
import com.example.employee.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private EmployeeRepository repo;

    @Autowired
    private JwtUtil jwtUtil;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public String registerAdmin(RegisterRequest request) {
        if (!request.password.equals(request.confirmPassword)) {
            throw new RuntimeException("Passwords do not match");
        }

        Optional<Employee> existing = repo.findByEmail(request.email);
        if (existing.isPresent()) throw new RuntimeException("Admin already exists");

        Employee admin = new Employee();
        admin.setEmail(request.email);
        admin.setPassword(encoder.encode(request.password));

        repo.save(admin);
        return "Admin registered successfully";
    }

    public String loginAdmin(LoginRequest request) {
        Employee admin = repo.findByEmail(request.email)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        if (!encoder.matches(request.password, admin.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtUtil.generateToken(admin.getEmail());
    }

    public Employee getAdmin(String id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Admin not found"));
    }

    public Employee fillDetails(String id, Employee update) {
        Employee admin = getAdmin(id);

        if (admin.getName() == null) admin.setName(update.getName());
        if (admin.getPhoneNo() == null) admin.setPhoneNo(update.getPhoneNo());
        if (admin.getPosition() == null) admin.setPosition(update.getPosition());
        if (admin.getDepartment() == null) admin.setDepartment(update.getDepartment());

        return repo.save(admin);
    }

    public Employee updateAdmin(String id, Employee updated) {
        // Retrieve the existing admin from the database
        Employee existingAdmin = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Admin not found"));

        // Update only the fields that are not email or password
        if (updated.getName() != null) {
            existingAdmin.setName(updated.getName());
        }
        if (updated.getPhoneNo() != null) {
            existingAdmin.setPhoneNo(updated.getPhoneNo());
        }
        if (updated.getPosition() != null) {
            existingAdmin.setPosition(updated.getPosition());
        }
        if (updated.getDepartment() != null) {
            existingAdmin.setDepartment(updated.getDepartment());
        }

        // Save the updated admin back to the database
        return repo.save(existingAdmin);
    }


    public void deleteAdmin(String id) {
        repo.deleteById(id);
    }
}
