package com.juaracoding.company.dto.request;

import jakarta.validation.constraints.NotEmpty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class DivisionRequest {

    @NotEmpty(message = "Name cannot be empty")
    @Schema(description = "Division name", example = "Marketing")
    @Pattern(regexp = "^[^<>]*$", message = "Name cannot contain angle brackets")
    private String name;

    @Schema(description = "Division description", example = "Responsible for marketing activities")
    @Pattern(regexp = "^[^<>]*$", message = "Description cannot contain angle brackets")
    private String description;
}
