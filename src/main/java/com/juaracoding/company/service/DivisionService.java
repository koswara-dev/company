package com.juaracoding.company.service;

import com.juaracoding.company.dto.request.DivisionRequest;
import com.juaracoding.company.dto.response.DivisionResponse;
import java.util.List;

public interface DivisionService {

    DivisionResponse createDivision(DivisionRequest divisionRequest);

    DivisionResponse getDivisionById(Long id);

    List<DivisionResponse> getAllDivisions();

    DivisionResponse updateDivision(Long id, DivisionRequest divisionRequest);

    void deleteDivision(Long id);
}
