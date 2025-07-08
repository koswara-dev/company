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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@RestController
@RequestMapping("/api/v1/divisions")
public class DivisionController {

    @Autowired
    private DivisionService divisionService;

    @Operation(summary = "Create a new division")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Division created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DivisionResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Response<DivisionResponse>> createDivision(@Valid @RequestBody DivisionRequest divisionRequest) {
        DivisionResponse divisionResponse = divisionService.createDivision(divisionRequest);
        Response<DivisionResponse> response = new Response<>();
        response.setSuccess(true);
        response.setMessage("Division created successfully");
        response.setData(divisionResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Get a division by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Division retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DivisionResponse.class))),
            @ApiResponse(responseCode = "404", description = "Division not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Response<DivisionResponse>> getDivisionById(@PathVariable Long id) {
        DivisionResponse divisionResponse = divisionService.getDivisionById(id);
        Response<DivisionResponse> response = new Response<>();
        response.setSuccess(true);
        response.setMessage("Division retrieved successfully");
        response.setData(divisionResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Get all divisions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Divisions retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DivisionResponse.class)))
    })
    @GetMapping
    public ResponseEntity<Response<List<DivisionResponse>>> getAllDivisions() {
        List<DivisionResponse> divisionResponses = divisionService.getAllDivisions();
        Response<List<DivisionResponse>> response = new Response<>();
        response.setSuccess(true);
        response.setMessage("Divisions retrieved successfully");
        response.setData(divisionResponses);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Update an existing division")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Division updated successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DivisionResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "404", description = "Division not found", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Response<DivisionResponse>> updateDivision(@PathVariable Long id, @Valid @RequestBody DivisionRequest divisionRequest) {
        DivisionResponse divisionResponse = divisionService.updateDivision(id, divisionRequest);
        Response<DivisionResponse> response = new Response<>();
        response.setSuccess(true);
        response.setMessage("Division updated successfully");
        response.setData(divisionResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Delete a division by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Division deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Division not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Void>> deleteDivision(@PathVariable Long id) {
        divisionService.deleteDivision(id);
        Response<Void> response = new Response<>();
        response.setSuccess(true);
        response.setMessage("Division deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}
