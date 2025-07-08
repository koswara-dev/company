package com.juaracoding.company.controller;

import com.juaracoding.company.dto.request.EmployeeRequest;
import com.juaracoding.company.dto.response.EmployeeResponse;
import com.juaracoding.company.dto.response.Response;
import com.juaracoding.company.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Response<EmployeeResponse>> createEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {
        EmployeeResponse employeeResponse = employeeService.createEmployee(employeeRequest);
        Response<EmployeeResponse> response = new Response<>();
        response.setSuccess(true);
        response.setMessage("Employee created successfully");
        response.setData(employeeResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<EmployeeResponse>> getEmployeeById(@PathVariable Long id) {
        EmployeeResponse employeeResponse = employeeService.getEmployeeById(id);
        Response<EmployeeResponse> response = new Response<>();
        response.setSuccess(true);
        response.setMessage("Employee retrieved successfully");
        response.setData(employeeResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Response<List<EmployeeResponse>>> getAllEmployees() {
        List<EmployeeResponse> employeeResponses = employeeService.getAllEmployees();
        Response<List<EmployeeResponse>> response = new Response<>();
        response.setSuccess(true);
        response.setMessage("Employees retrieved successfully");
        response.setData(employeeResponses);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<EmployeeResponse>> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeRequest employeeRequest) {
        EmployeeResponse employeeResponse = employeeService.updateEmployee(id, employeeRequest);
        Response<EmployeeResponse> response = new Response<>();
        response.setSuccess(true);
        response.setMessage("Employee updated successfully");
        response.setData(employeeResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Void>> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        Response<Void> response = new Response<>();
        response.setSuccess(true);
        response.setMessage("Employee deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}
