package com.example.assignment3.patient;

import com.example.assignment3.BaseControllerTest;
import com.example.assignment3.patient.model.dto.PatientDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.List;

import static com.example.assignment3.TestCreationFactory.*;
import static com.example.assignment3.URLMapping.ENTITY;
import static com.example.assignment3.URLMapping.PATIENT;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PatientControllerTest extends BaseControllerTest {

    @InjectMocks
    private PatientController controller;

    @Mock
    private PatientService patientService;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        controller = new PatientController(patientService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void allPatients() throws Exception {
        List<PatientDTO> books = listOf(PatientDTO.class);

        when(patientService.findAll()).thenReturn(books);
        ResultActions response = mockMvc.perform(get(PATIENT));

        response.andExpect(status().isOk())
                .andExpect(jsonContentToBe(books));
    }

    @Test
    void create() throws Exception {
        PatientDTO patientDTO = PatientDTO.builder()
                .CNP(randomLong())
                .address(randomString())
                .identityCard(randomString())
                .dateOfBirth(randomString())
                .name(randomString())
                .build();

        when(patientService.create(patientDTO)).thenReturn(patientDTO);

        ResultActions result = performPostWithRequestBody(PATIENT, patientDTO);
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(patientDTO));
    }

    @Test
    void edit() throws Exception {
        long id = randomLong();
        PatientDTO patientDTO = PatientDTO.builder()
                .id(id)
                .CNP(randomLong())
                .address(randomString())
                .identityCard(randomString())
                .dateOfBirth(randomString())
                .name(randomString())
                .build();

        when(patientService.edit(id, patientDTO)).thenReturn(patientDTO);

        ResultActions result = performPutWithRequestBodyAndPathVariable (PATIENT + ENTITY, patientDTO, id);
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(patientDTO));
    }

    @Test
    void delete() throws Exception {
        long id = randomLong();

        doNothing().when(patientService).delete(id);

        ResultActions result = performDeleteWithPathVariable(PATIENT + ENTITY, id);
        result.andExpect(status().isOk());
    }
}