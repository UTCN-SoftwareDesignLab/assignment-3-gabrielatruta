package com.example.assignment3.patient;


import com.example.assignment3.patient.model.dto.PatientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.assignment3.URLMapping.ENTITY;
import static com.example.assignment3.URLMapping.PATIENT;

@RestController
@RequestMapping(PATIENT)
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public List<PatientDTO> allPatients() {
        return patientService.findAll();
    }

    @PostMapping
    public PatientDTO create(@RequestBody PatientDTO patientDTO) {
        return  patientService.create(patientDTO);
    }

    @PutMapping(ENTITY)
    public PatientDTO edit(@PathVariable Long id, @RequestBody PatientDTO patientDTO){
        return patientService.edit(id, patientDTO);
    }

    @DeleteMapping(ENTITY)
    public void delete(@PathVariable Long id) {
        patientService.delete(id);
    }
}
