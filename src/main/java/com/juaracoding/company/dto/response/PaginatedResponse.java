package com.juaracoding.company.dto.response;

import lombok.Data;
import java.util.List;

@Data
public class PaginatedResponse<T> {
    private Boolean success;
    private String message;
    private List<T> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean last;
    private boolean first;
}
