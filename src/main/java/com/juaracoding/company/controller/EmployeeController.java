package com.juaracoding.company.controller;

import com.juaracoding.company.dto.request.EmployeeRequest;
import com.juaracoding.company.dto.response.EmployeeResponse;
import com.juaracoding.company.dto.response.PaginatedResponse;
import com.juaracoding.company.dto.response.Response;
import com.juaracoding.company.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Operation(summary = "Create a new employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Employee created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Response<EmployeeResponse>> createEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {
        EmployeeResponse employeeResponse = employeeService.createEmployee(employeeRequest);
        Response<EmployeeResponse> response = new Response<>();
        response.setSuccess(true);
        response.setMessage("Employee created successfully");
        response.setData(employeeResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Get an employee by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeResponse.class))),
            @ApiResponse(responseCode = "404", description = "Employee not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Response<EmployeeResponse>> getEmployeeById(@PathVariable Long id) {
        EmployeeResponse employeeResponse = employeeService.getEmployeeById(id);
        Response<EmployeeResponse> response = new Response<>();
        response.setSuccess(true);
        response.setMessage("Employee retrieved successfully");
        response.setData(employeeResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Get all employees (paginated, searchable by name, and filterable by division)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employees retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaginatedResponse.class)))
    })
    @GetMapping
    public ResponseEntity<PaginatedResponse<EmployeeResponse>> getAllEmployees(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long divisionId,
            @PageableDefault(size = 10) Pageable pageable) {
        Page<EmployeeResponse> employeeResponsesPage;
        if (name != null && !name.isEmpty()) {
            employeeResponsesPage = employeeService.searchEmployees(name, pageable);
        } else if (divisionId != null) {
            employeeResponsesPage = employeeService.filterEmployeesByDivision(divisionId, pageable);
        }
        else {
            employeeResponsesPage = employeeService.getAllEmployees(pageable);
        }

        PaginatedResponse<EmployeeResponse> response = new PaginatedResponse<>();
        response.setSuccess(true);
        response.setMessage("Employees retrieved successfully");
        response.setContent(employeeResponsesPage.getContent());
        response.setPage(employeeResponsesPage.getNumber());
        response.setSize(employeeResponsesPage.getSize());
        response.setTotalElements(employeeResponsesPage.getTotalElements());
        response.setTotalPages(employeeResponsesPage.getTotalPages());
        response.setLast(employeeResponsesPage.isLast());
        response.setFirst(employeeResponsesPage.isFirst());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//     @Operation(summary = "Get all employees")
//     @ApiResponses(value = {
//             @ApiResponse(responseCode = "200", description = "Employees retrieved successfully",
//                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeResponse.class)))
//     })
//     @GetMapping
//     public ResponseEntity<Response<List<EmployeeResponse>>> getAllEmployees() {
//         List<EmployeeResponse> employeeResponses = employeeService.getAllEmployees();
//         Response<List<EmployeeResponse>> response = new Response<>();
//         response.setSuccess(true);
//         response.setMessage("Employees retrieved successfully");
//         response.setData(employeeResponses);
//         return new ResponseEntity<>(response, HttpStatus.OK);
//     }

    @Operation(summary = "Update an existing employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee updated successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "404", description = "Employee not found", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Response<EmployeeResponse>> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeRequest employeeRequest) {
        EmployeeResponse employeeResponse = employeeService.updateEmployee(id, employeeRequest);
        Response<EmployeeResponse> response = new Response<>();
        response.setSuccess(true);
        response.setMessage("Employee updated successfully");
        response.setData(employeeResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Delete an employee by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Employee deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Employee not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Void>> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        Response<Void> response = new Response<>();
        response.setSuccess(true);
        response.setMessage("Employee deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}
