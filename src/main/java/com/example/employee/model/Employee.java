package com.example.employee.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import jakarta.validation.constraints.*;

@Document(collection = "employees")
public class Employee {

    @Id
    private String id;

    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "PhoneNo is required")
    private String phoneNo;
    @NotBlank(message = "Postion is required")
    private String position;
    @NotBlank(message = "Department is required")
    private String department;
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{6,}$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character"
    )
    private String password;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Employee() {}

    // Getters and Setters
    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getPhoneNo() { return phoneNo; }

    public void setPhoneNo(String phoneNo) { this.phoneNo = phoneNo; }

    public String getPosition() { return position; }

    public void setPosition(String position) { this.position = position; }

    public String getDepartment() { return department; }

    public void setDepartment(String department) { this.department = department; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
