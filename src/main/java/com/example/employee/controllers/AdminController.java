package com.example.employee.controllers;

import com.example.employee.model.Employee;
import com.example.employee.payload.LoginRequest;
import com.example.employee.payload.RegisterRequest;
import com.example.employee.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired private AdminService service;

    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequest request) {
        return service.registerAdmin(request);
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginRequest request) {
        return service.loginAdmin(request);
    }

    @GetMapping("/getadmin/{id}")
    public Employee getAdmin(@PathVariable String id) {
        return service.getAdmin(id);
    }

    @PatchMapping("/fillDetails/{id}")
    public Employee fillDetails(@PathVariable String id,@Valid @RequestBody Employee updated) {
        return service.fillDetails(id, updated);
    }

    @PutMapping("/updateAdmin/{id}")
    public Employee updateAdmin(@PathVariable String id, @RequestBody Employee updated) {
        return service.updateAdmin(id, updated);
    }


    @DeleteMapping("/deleteAdmin/{id}")
    public String deleteAdmin(@PathVariable String id) {
        service.deleteAdmin(id);
        return "Admin deleted successfully";
    }
}
