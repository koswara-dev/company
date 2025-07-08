package com.juaracoding.company.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class DivisionRequest {

    @NotEmpty(message = "Name cannot be empty")
    @Pattern(regexp = "^[^<>]*$", message = "Name cannot contain angle brackets")
    private String name;

    @NotEmpty(message = "Description cannot be empty")
    @Pattern(regexp = "^[^<>]*$", message = "Description cannot contain angle brackets")
    private String description;
}
