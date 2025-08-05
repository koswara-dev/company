package com.juaracoding.company.service;

import com.juaracoding.company.dto.request.EmployeeRequest;
import com.juaracoding.company.dto.response.EmployeeResponse;
import com.juaracoding.company.exception.ResourceNotFoundException;
import com.juaracoding.company.model.Division;
import com.juaracoding.company.model.Employee;
import com.juaracoding.company.repository.DivisionRepository;
import com.juaracoding.company.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DivisionRepository divisionRepository;

    @Override
    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {
        Division division = divisionRepository.findById(employeeRequest.getDivisionId())
                .orElseThrow(() -> new ResourceNotFoundException("Division not found with id: " + employeeRequest.getDivisionId()));

        Employee employee = new Employee();
        employee.setName(employeeRequest.getName());
        employee.setEmail(employeeRequest.getEmail());
        employee.setDivision(division);

        Employee savedEmployee = employeeRepository.save(employee);

        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setId(savedEmployee.getId());
        employeeResponse.setName(savedEmployee.getName());
        employeeResponse.setEmail(savedEmployee.getEmail());
		employeeResponse.setCreatedAt(savedEmployee.getCreatedAt());
		employeeResponse.setUpdatedAt(savedEmployee.getUpdatedAt());
        employeeResponse.setDivisionName(division.getName());

        return employeeResponse;
    }

    @Override
    public EmployeeResponse getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setId(employee.getId());
        employeeResponse.setName(employee.getName());
        employeeResponse.setEmail(employee.getEmail());
		employeeResponse.setCreatedAt(employee.getCreatedAt());
		employeeResponse.setUpdatedAt(employee.getUpdatedAt());
        employeeResponse.setDivisionName(employee.getDivision().getName());

        return employeeResponse;
    }

    @Override
    public List<EmployeeResponse> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();

        return employees.stream()
                .map(employee -> {
                    EmployeeResponse employeeResponse = new EmployeeResponse();
                    employeeResponse.setId(employee.getId());
                    employeeResponse.setName(employee.getName());
                    employeeResponse.setEmail(employee.getEmail());

                    employeeResponse.setCreatedAt(employee.getCreatedAt());
					employeeResponse.setUpdatedAt(employee.getUpdatedAt());
                    employeeResponse.setDivisionName(employee.getDivision().getName());

                    return employeeResponse;
                })
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeResponse updateEmployee(Long id, EmployeeRequest employeeRequest) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

        Division division = divisionRepository.findById(employeeRequest.getDivisionId())
                .orElseThrow(() -> new ResourceNotFoundException("Division not found with id: " + employeeRequest.getDivisionId()));

        employee.setName(employeeRequest.getName());
        employee.setEmail(employeeRequest.getEmail());
        employee.setDivision(division);

        Employee updatedEmployee = employeeRepository.save(employee);

        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setId(updatedEmployee.getId());
        employeeResponse.setName(updatedEmployee.getName());
        employeeResponse.setEmail(updatedEmployee.getEmail());

        employeeResponse.setCreatedAt(updatedEmployee.getCreatedAt());
		employeeResponse.setUpdatedAt(updatedEmployee.getUpdatedAt());
        employeeResponse.setDivisionName(division.getName());

        return employeeResponse;
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

        employeeRepository.delete(employee);
    }

    @Override
    public Page<EmployeeResponse> getAllEmployees(Pageable pageable) {
        Page<Employee> employeesPage = employeeRepository.findAll(pageable);
        return employeesPage.map(employee -> {
            EmployeeResponse employeeResponse = new EmployeeResponse();
            employeeResponse.setId(employee.getId());
            employeeResponse.setName(employee.getName());
            employeeResponse.setEmail(employee.getEmail());
            employeeResponse.setCreatedAt(employee.getCreatedAt());
            employeeResponse.setUpdatedAt(employee.getUpdatedAt());
            employeeResponse.setDivisionName(employee.getDivision().getName());
            return employeeResponse;
        });
    }

    @Override
    public Page<EmployeeResponse> searchEmployees(String name, Pageable pageable) {
        Page<Employee> employeesPage = employeeRepository.findByNameContainingIgnoreCase(name, pageable);
        return employeesPage.map(employee -> {
            EmployeeResponse employeeResponse = new EmployeeResponse();
            employeeResponse.setId(employee.getId());
            employeeResponse.setName(employee.getName());
            employeeResponse.setEmail(employee.getEmail());
            employeeResponse.setCreatedAt(employee.getCreatedAt());
            employeeResponse.setUpdatedAt(employee.getUpdatedAt());
            employeeResponse.setDivisionName(employee.getDivision().getName());
            return employeeResponse;
        });
    }

    @Override
    public Page<EmployeeResponse> filterEmployeesByDivision(Long divisionId, Pageable pageable) {
        Division division = divisionRepository.findById(divisionId)
                .orElseThrow(() -> new ResourceNotFoundException("Division not found with id: " + divisionId));
        Page<Employee> employeesPage = employeeRepository.findByDivision(division, pageable);
        return employeesPage.map(employee -> {
            EmployeeResponse employeeResponse = new EmployeeResponse();
            employeeResponse.setId(employee.getId());
            employeeResponse.setName(employee.getName());
            employeeResponse.setEmail(employee.getEmail());
            employeeResponse.setCreatedAt(employee.getCreatedAt());
            employeeResponse.setUpdatedAt(employee.getUpdatedAt());
            employeeResponse.setDivisionName(employee.getDivision().getName());
            return employeeResponse;
        });
    }
}
