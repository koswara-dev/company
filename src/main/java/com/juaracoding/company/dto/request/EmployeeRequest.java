package com.juaracoding.company.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class EmployeeRequest {

    @NotEmpty(message = "Name cannot be empty")
    @Schema(description = "Employee name", example = "John Doe")
    @Pattern(regexp = "^[^<>]*$", message = "Name cannot contain angle brackets")
    private String name;

    @Schema(description = "Employee email", example = "john.doe@example.com")
    @Email(message = "Email should be valid")
    private String email;

    @Schema(description = "Division ID", example = "1")
    @NotNull(message = "Division ID cannot be null")
    private Long divisionId;
}
