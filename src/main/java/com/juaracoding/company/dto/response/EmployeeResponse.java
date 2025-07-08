package com.juaracoding.company.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class EmployeeResponse {

    private Long id;
    private String name;
    private String email;
    private String divisionName;
    private Date createdAt;
    private Date updatedAt;
}
