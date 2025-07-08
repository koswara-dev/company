package com.juaracoding.company.service;

import com.juaracoding.company.dto.request.DivisionRequest;
import com.juaracoding.company.dto.response.DivisionResponse;
import com.juaracoding.company.exception.ResourceNotFoundException;
import com.juaracoding.company.model.Division;
import com.juaracoding.company.repository.DivisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DivisionServiceImpl implements DivisionService {

    @Autowired
    private DivisionRepository divisionRepository;

    @Override
    public DivisionResponse createDivision(DivisionRequest divisionRequest) {
        Division division = new Division();
        division.setName(divisionRequest.getName());
        division.setDescription(divisionRequest.getDescription());

        Division savedDivision = divisionRepository.save(division);

        DivisionResponse divisionResponse = new DivisionResponse();
        divisionResponse.setId(savedDivision.getId());
        divisionResponse.setName(savedDivision.getName());
        divisionResponse.setDescription(savedDivision.getDescription());

        return divisionResponse;
    }

    @Override
    public DivisionResponse getDivisionById(Long id) {
        Division division = divisionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Division not found with id: " + id));

        DivisionResponse divisionResponse = new DivisionResponse();
        divisionResponse.setId(division.getId());
        divisionResponse.setName(division.getName());
        divisionResponse.setDescription(division.getDescription());

        return divisionResponse;
    }

    @Override
    public List<DivisionResponse> getAllDivisions() {
        List<Division> divisions = divisionRepository.findAll();

        return divisions.stream()
                .map(division -> {
                    DivisionResponse divisionResponse = new DivisionResponse();
                    divisionResponse.setId(division.getId());
                    divisionResponse.setName(division.getName());
                    divisionResponse.setDescription(division.getDescription());
                    return divisionResponse;
                })
                .collect(Collectors.toList());
    }

    @Override
    public DivisionResponse updateDivision(Long id, DivisionRequest divisionRequest) {
        Division division = divisionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Division not found with id: " + id));

        division.setName(divisionRequest.getName());
        division.setDescription(divisionRequest.getDescription());

        Division updatedDivision = divisionRepository.save(division);

        DivisionResponse divisionResponse = new DivisionResponse();
        divisionResponse.setId(updatedDivision.getId());
        divisionResponse.setName(updatedDivision.getName());
        divisionResponse.setDescription(updatedDivision.getDescription());

        return divisionResponse;
    }

    @Override
    public void deleteDivision(Long id) {
        Division division = divisionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Division not found with id: " + id));

        divisionRepository.delete(division);
    }
}
