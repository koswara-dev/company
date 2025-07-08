package com.juaracoding.company.controller;

import com.juaracoding.company.dto.request.DivisionRequest;
import com.juaracoding.company.dto.response.DivisionResponse;
import com.juaracoding.company.dto.response.Response;
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
    public ResponseEntity<Response<DivisionResponse>> createDivision(@Valid @RequestBody DivisionRequest divisionRequest) {
        DivisionResponse divisionResponse = divisionService.createDivision(divisionRequest);
        Response<DivisionResponse> response = new Response<>();
        response.setSuccess(true);
        response.setMessage("Division created successfully");
        response.setData(divisionResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<DivisionResponse>> getDivisionById(@PathVariable Long id) {
        DivisionResponse divisionResponse = divisionService.getDivisionById(id);
        Response<DivisionResponse> response = new Response<>();
        response.setSuccess(true);
        response.setMessage("Division retrieved successfully");
        response.setData(divisionResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Response<List<DivisionResponse>>> getAllDivisions() {
        List<DivisionResponse> divisionResponses = divisionService.getAllDivisions();
        Response<List<DivisionResponse>> response = new Response<>();
        response.setSuccess(true);
        response.setMessage("Divisions retrieved successfully");
        response.setData(divisionResponses);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<DivisionResponse>> updateDivision(@PathVariable Long id, @Valid @RequestBody DivisionRequest divisionRequest) {
        DivisionResponse divisionResponse = divisionService.updateDivision(id, divisionRequest);
        Response<DivisionResponse> response = new Response<>();
        response.setSuccess(true);
        response.setMessage("Division updated successfully");
        response.setData(divisionResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Void>> deleteDivision(@PathVariable Long id) {
        divisionService.deleteDivision(id);
        Response<Void> response = new Response<>();
        response.setSuccess(true);
        response.setMessage("Division deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}
