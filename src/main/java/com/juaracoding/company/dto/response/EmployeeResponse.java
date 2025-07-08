package com.juaracoding.company.dto.response;

import lombok.Data;

@Data
public class EmployeeResponse {

    private Long id;
    private String name;
    private String email;
    private DivisionResponse division;
}
