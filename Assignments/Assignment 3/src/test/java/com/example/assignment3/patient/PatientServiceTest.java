package com.example.assignment3.patient;

import com.example.assignment3.TestCreationFactory;
import com.example.assignment3.patient.model.Patient;
import com.example.assignment3.patient.model.dto.PatientDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.example.assignment3.TestCreationFactory.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class PatientServiceTest {

    @InjectMocks
    private PatientService patientService;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private PatientMapper patientMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        patientService = new PatientService(patientRepository, patientMapper);
    }

    @Test
    void findAll() {
        List<Patient> allPatients = listOf(Patient.class);
        when(patientRepository.findAll()).thenReturn(allPatients);

        List<PatientDTO> expected = patientService.findAll();

        assertEquals(allPatients.size(), expected.size());
    }

}