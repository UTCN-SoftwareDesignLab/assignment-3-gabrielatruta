package com.example.assignment3.patient;

import com.example.assignment3.patient.model.Patient;
import com.example.assignment3.patient.model.dto.PatientDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    PatientDTO toDTO (Patient patient);

    Patient fromDTO (PatientDTO patientDTO);
}
