package com.juaracoding.company.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class EmployeeRequest {

    @NotEmpty(message = "Name cannot be empty")
    @Pattern(regexp = "^[^<>]*$", message = "Name cannot contain angle brackets")
    private String name;

    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Division ID cannot be null")
    private Long divisionId;
}
