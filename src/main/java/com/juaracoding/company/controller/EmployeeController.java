package com.juaracoding.company.controller;

import com.juaracoding.company.dto.request.EmployeeRequest;
import com.juaracoding.company.dto.response.EmployeeResponse;
import com.juaracoding.company.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {
        EmployeeResponse employeeResponse = employeeService.createEmployee(employeeRequest);
        return new ResponseEntity<>(employeeResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Long id) {
        EmployeeResponse employeeResponse = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        List<EmployeeResponse> employeeResponses = employeeService.getAllEmployees();
        return new ResponseEntity<>(employeeResponses, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeRequest employeeRequest) {
        EmployeeResponse employeeResponse = employeeService.updateEmployee(id, employeeRequest);
        return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
