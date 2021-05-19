package com.example.assignment3.consultation;

import com.example.assignment3.BaseControllerTest;
import com.example.assignment3.consultation.model.dto.ConsultationDTO;
import com.example.assignment3.patient.PatientRepository;
import com.example.assignment3.patient.model.Patient;
import com.example.assignment3.user.RoleRepository;
import com.example.assignment3.user.UserRepository;
import com.example.assignment3.user.model.Role;
import com.example.assignment3.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.example.assignment3.TestCreationFactory.*;
import static com.example.assignment3.URLMapping.CONSULTATION;
import static com.example.assignment3.URLMapping.ENTITY;
import static com.example.assignment3.user.model.ERole.DOCTOR;
import static java.util.Optional.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ConsultationControllerTest extends BaseControllerTest {

    @InjectMocks
    ConsultationController consultationController;

    @Mock
    ConsultationService consultationService;

    @Mock
    UserRepository userRepository;

    @Mock
    RoleRepository roleRepository;

    @Mock
    PatientRepository patientRepository;

    @BeforeEach
    protected void setup() {
        super.setUp();
        consultationController = new ConsultationController(consultationService);
        mockMvc = MockMvcBuilders.standaloneSetup(consultationController).build();
    }

    @Test
    void findAll() throws Exception {

        List<ConsultationDTO> consultationList = listOf(ConsultationDTO.class);

        when(consultationService.findAllConsultations()).thenReturn(consultationList);
        ResultActions response = mockMvc.perform(get(CONSULTATION));

        response.andExpect(status().isOk())
                .andExpect(jsonContentToBe(consultationList));

    }

    @Test
    void create() throws Exception {
        Role role = Role.builder()
                .name(DOCTOR)
                .build();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        roleRepository.save(role);

        User user = User.builder()
                .password("ahaajdkaj121!")
                .email("ahaha@haha.com")
                .username("what")
                .roles(roles)
                .build();
        userRepository.save(user);

        Patient patient = Patient.builder()
                .CNP(2990318337901L)
                .address("stata")
                .identityCard("SX908671")
                .dateOfBirth("18.03.1999")
                .name("Gabi")
                .build();
        patientRepository.save(patient);

        ConsultationDTO reqConsultation = ConsultationDTO.builder()
                .dateOfConsultation(randomString())
                .diagnosis("stupidity")
                .doctorId(userRepository.findByUsername(user.getUsername()).orElseThrow(
                        () -> new UsernameNotFoundException("User Not Found with username: " + user.getUsername())).getId())
                .patientId(patientRepository.findPatientByCNP(patient.getCNP()).orElseThrow(
                        () -> new UsernameNotFoundException("Patient Not Found with CNP: " + patient.getCNP())).getId())
                .build();

        when(consultationService.create(reqConsultation)).thenReturn(of(reqConsultation));

       ResultActions result = performPostWithRequestBody(CONSULTATION, reqConsultation);
       result.andExpect(status().isOk())
               .andExpect(jsonContentToBe(reqConsultation));
    }


    @Test
    void delete() throws Exception {
        long id = randomLong();

        doNothing().when(consultationService).delete(id);

        ResultActions result = performDeleteWithPathVariable(CONSULTATION + ENTITY, id);
        result.andExpect(status().isOk());
    }
}