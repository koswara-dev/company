package com.juaracoding.company.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class DivisionResponse {

    private Long id;
    private String name;
    private String description;
    private Date createdAt;
    private Date updatedAt;
}
