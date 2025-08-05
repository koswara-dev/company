package com.juaracoding.company.service;

import com.juaracoding.company.dto.request.EmployeeRequest;
import com.juaracoding.company.dto.response.EmployeeResponse;
import com.juaracoding.company.exception.ResourceNotFoundException;
import com.juaracoding.company.model.Division;
import com.juaracoding.company.model.Employee;
import com.juaracoding.company.repository.DivisionRepository;
import com.juaracoding.company.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private DivisionRepository divisionRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;
    private Division division;
    private EmployeeRequest employeeRequest;
    private EmployeeResponse employeeResponse;

    @BeforeEach
    void setUp() {
        division = new Division();
        division.setId(1L);
        division.setName("IT");
        division.setDescription("Information Technology Department");
        division.setCreatedAt(new Date());
        division.setUpdatedAt(new Date());

        employee = new Employee();
        employee.setId(1L);
        employee.setName("John Doe");
        employee.setEmail("john.doe@example.com");
        employee.setDivision(division);
        employee.setCreatedAt(new Date());
        employee.setUpdatedAt(new Date());

        employeeRequest = new EmployeeRequest();
        employeeRequest.setName("John Doe");
        employeeRequest.setEmail("john.doe@example.com");
        employeeRequest.setDivisionId(1L);

        employeeResponse = new EmployeeResponse();
        employeeResponse.setId(1L);
        employeeResponse.setName("John Doe");
        employeeResponse.setEmail("john.doe@example.com");
        employeeResponse.setDivisionName("IT");
        employeeResponse.setCreatedAt(employee.getCreatedAt());
        employeeResponse.setUpdatedAt(employee.getUpdatedAt());
    }

    @Test
    void createEmployee_success() {
        when(divisionRepository.findById(1L)).thenReturn(Optional.of(division));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        EmployeeResponse result = employeeService.createEmployee(employeeRequest);

        assertNotNull(result);
        assertEquals(employee.getId(), result.getId());
        assertEquals(employee.getName(), result.getName());
        assertEquals(employee.getEmail(), result.getEmail());
        assertEquals(employee.getDivision().getName(), result.getDivisionName());
        verify(divisionRepository, times(1)).findById(1L);
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void createEmployee_divisionNotFound() {
        when(divisionRepository.findById(2L)).thenReturn(Optional.empty());
        employeeRequest.setDivisionId(2L);

        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
            employeeService.createEmployee(employeeRequest);
        });

        assertEquals("Division not found with id: 2", thrown.getMessage());
        verify(divisionRepository, times(1)).findById(2L);
        verify(employeeRepository, never()).save(any(Employee.class));
    }

    @Test
    void getEmployeeById_success() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        EmployeeResponse result = employeeService.getEmployeeById(1L);

        assertNotNull(result);
        assertEquals(employee.getId(), result.getId());
        assertEquals(employee.getName(), result.getName());
        assertEquals(employee.getEmail(), result.getEmail());
        assertEquals(employee.getDivision().getName(), result.getDivisionName());
        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    void getEmployeeById_notFound() {
        when(employeeRepository.findById(2L)).thenReturn(Optional.empty());

        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
            employeeService.getEmployeeById(2L);
        });

        assertEquals("Employee not found with id: 2", thrown.getMessage());
        verify(employeeRepository, times(1)).findById(2L);
    }

    @Test
    void updateEmployee_success() {
        EmployeeRequest updatedRequest = new EmployeeRequest();
        updatedRequest.setName("Jane Doe");
        updatedRequest.setEmail("jane.doe@example.com");
        updatedRequest.setDivisionId(1L);

        Employee updatedEmployee = new Employee();
        updatedEmployee.setId(1L);
        updatedEmployee.setName("Jane Doe");
        updatedEmployee.setEmail("jane.doe@example.com");
        updatedEmployee.setDivision(division);
        updatedEmployee.setCreatedAt(employee.getCreatedAt());
        updatedEmployee.setUpdatedAt(new Date());

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(divisionRepository.findById(1L)).thenReturn(Optional.of(division));
        when(employeeRepository.save(any(Employee.class))).thenReturn(updatedEmployee);

        EmployeeResponse result = employeeService.updateEmployee(1L, updatedRequest);

        assertNotNull(result);
        assertEquals(updatedEmployee.getId(), result.getId());
        assertEquals(updatedEmployee.getName(), result.getName());
        assertEquals(updatedEmployee.getEmail(), result.getEmail());
        assertEquals(updatedEmployee.getDivision().getName(), result.getDivisionName());
        verify(employeeRepository, times(1)).findById(1L);
        verify(divisionRepository, times(1)).findById(1L);
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void deleteEmployee_success() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        doNothing().when(employeeRepository).delete(employee);

        employeeService.deleteEmployee(1L);

        verify(employeeRepository, times(1)).findById(1L);
        verify(employeeRepository, times(1)).delete(employee);
    }
}
