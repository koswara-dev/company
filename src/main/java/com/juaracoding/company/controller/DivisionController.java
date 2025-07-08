package com.juaracoding.company.controller;

import com.juaracoding.company.dto.request.DivisionRequest;
import com.juaracoding.company.dto.response.DivisionResponse;
import com.juaracoding.company.service.DivisionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/divisions")
public class DivisionController {

    @Autowired
    private DivisionService divisionService;

    @PostMapping
    public ResponseEntity<DivisionResponse> createDivision(@Valid @RequestBody DivisionRequest divisionRequest) {
        DivisionResponse divisionResponse = divisionService.createDivision(divisionRequest);
        return new ResponseEntity<>(divisionResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DivisionResponse> getDivisionById(@PathVariable Long id) {
        DivisionResponse divisionResponse = divisionService.getDivisionById(id);
        return new ResponseEntity<>(divisionResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<DivisionResponse>> getAllDivisions() {
        List<DivisionResponse> divisionResponses = divisionService.getAllDivisions();
        return new ResponseEntity<>(divisionResponses, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DivisionResponse> updateDivision(@PathVariable Long id, @Valid @RequestBody DivisionRequest divisionRequest) {
        DivisionResponse divisionResponse = divisionService.updateDivision(id, divisionRequest);
        return new ResponseEntity<>(divisionResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDivision(@PathVariable Long id) {
        divisionService.deleteDivision(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
