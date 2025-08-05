package com.juaracoding.company.service;

import com.juaracoding.company.dto.request.EmployeeRequest;
import com.juaracoding.company.dto.response.EmployeeResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {

    EmployeeResponse createEmployee(EmployeeRequest employeeRequest);

    EmployeeResponse getEmployeeById(Long id);

    List<EmployeeResponse> getAllEmployees();

    EmployeeResponse updateEmployee(Long id, EmployeeRequest employeeRequest);

    void deleteEmployee(Long id);

    Page<EmployeeResponse> getAllEmployees(Pageable pageable);

    Page<EmployeeResponse> searchEmployees(String name, Pageable pageable);

    Page<EmployeeResponse> filterEmployeesByDivision(Long divisionId, Pageable pageable);
}
