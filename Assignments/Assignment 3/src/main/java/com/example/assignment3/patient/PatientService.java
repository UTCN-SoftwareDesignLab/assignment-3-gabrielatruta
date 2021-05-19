package com.example.assignment3.patient;

import com.example.assignment3.patient.model.Patient;
import com.example.assignment3.patient.model.dto.PatientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public Patient findByID(Long ID){
        return patientRepository.findById(ID)
                .orElseThrow(() -> new EntityNotFoundException("Patient with id = " + ID + " not found!"));
    }

    public List<PatientDTO> findAll() {
        return patientRepository.findAll().stream()
                .map(patientMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PatientDTO create(PatientDTO patientDTO){
        return patientMapper.toDTO(patientRepository.save(
                patientMapper.fromDTO(patientDTO)
        ));
    }

    public PatientDTO edit(Long ID, PatientDTO patient) {
        Patient actPatient = findByID(ID);

        actPatient.setAddress(patient.getAddress());
        actPatient.setCNP(patient.getCNP());
        actPatient.setDateOfBirth(patient.getDateOfBirth());
        actPatient.setName(patient.getName());
        actPatient.setIdentityCard(patient.getIdentityCard());

        return patientMapper.toDTO(
                patientRepository.save(actPatient)
        );
    }

    public void delete(Long id) {
        patientRepository.deleteById(id);
    }

}
