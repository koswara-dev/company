package com.juaracoding.company.service;

import com.juaracoding.company.dto.request.DivisionRequest;
import com.juaracoding.company.dto.response.DivisionResponse;
import com.juaracoding.company.exception.ResourceNotFoundException;
import com.juaracoding.company.model.Division;
import com.juaracoding.company.repository.DivisionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DivisionServiceImplTest {

    @Mock
    private DivisionRepository divisionRepository;

    @InjectMocks
    private DivisionServiceImpl divisionService;

    private Division division;
    private DivisionRequest divisionRequest;
    private DivisionResponse divisionResponse;

    @BeforeEach
    void setUp() {
        division = new Division();
        division.setId(1L);
        division.setName("Finance");
        division.setDescription("Financial Department");
        division.setCreatedAt(new Date());
        division.setUpdatedAt(new Date());

        divisionRequest = new DivisionRequest();
        divisionRequest.setName("Finance");
        divisionRequest.setDescription("Financial Department");

        divisionResponse = new DivisionResponse();
        divisionResponse.setId(1L);
        divisionResponse.setName("Finance");
        divisionResponse.setDescription("Financial Department");
        divisionResponse.setCreatedAt(division.getCreatedAt());
        divisionResponse.setUpdatedAt(division.getUpdatedAt());
    }

    @Test
    void createDivision_success() {
        when(divisionRepository.save(any(Division.class))).thenReturn(division);

        DivisionResponse result = divisionService.createDivision(divisionRequest);

        assertNotNull(result);
        assertEquals(division.getId(), result.getId());
        assertEquals(division.getName(), result.getName());
        assertEquals(division.getDescription(), result.getDescription());
        verify(divisionRepository, times(1)).save(any(Division.class));
    }

    @Test
    void getDivisionById_success() {
        when(divisionRepository.findById(1L)).thenReturn(Optional.of(division));

        DivisionResponse result = divisionService.getDivisionById(1L);

        assertNotNull(result);
        assertEquals(division.getId(), result.getId());
        assertEquals(division.getName(), result.getName());
        assertEquals(division.getDescription(), result.getDescription());
        verify(divisionRepository, times(1)).findById(1L);
    }

    @Test
    void getDivisionById_notFound() {
        when(divisionRepository.findById(2L)).thenReturn(Optional.empty());

        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
            divisionService.getDivisionById(2L);
        });

        assertEquals("Division not found with id: 2", thrown.getMessage());
        verify(divisionRepository, times(1)).findById(2L);
    }

    @Test
    void updateDivision_success() {
        DivisionRequest updatedRequest = new DivisionRequest();
        updatedRequest.setName("HR Updated");
        updatedRequest.setDescription("Human Resources Department Updated");

        Division updatedDivision = new Division();
        updatedDivision.setId(1L);
        updatedDivision.setName("HR Updated");
        updatedDivision.setDescription("Human Resources Department Updated");
        updatedDivision.setCreatedAt(division.getCreatedAt());
        updatedDivision.setUpdatedAt(new Date());

        when(divisionRepository.findById(1L)).thenReturn(Optional.of(division));
        when(divisionRepository.save(any(Division.class))).thenReturn(updatedDivision);

        DivisionResponse result = divisionService.updateDivision(1L, updatedRequest);

        assertNotNull(result);
        assertEquals(updatedDivision.getId(), result.getId());
        assertEquals(updatedDivision.getName(), result.getName());
        assertEquals(updatedDivision.getDescription(), result.getDescription());
        verify(divisionRepository, times(1)).findById(1L);
        verify(divisionRepository, times(1)).save(any(Division.class));
    }

    @Test
    void deleteDivision_success() {
        when(divisionRepository.findById(1L)).thenReturn(Optional.of(division));
        doNothing().when(divisionRepository).delete(division);

        divisionService.deleteDivision(1L);

        verify(divisionRepository, times(1)).findById(1L);
        verify(divisionRepository, times(1)).delete(division);
    }
}
