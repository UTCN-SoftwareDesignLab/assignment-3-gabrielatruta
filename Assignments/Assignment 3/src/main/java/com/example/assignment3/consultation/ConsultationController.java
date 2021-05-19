package com.example.assignment3.consultation;

import com.example.assignment3.consultation.model.dto.ConsultationDTO;
import com.example.assignment3.patient.model.Patient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.assignment3.URLMapping.*;

@RestController
@RequestMapping(CONSULTATION)
@RequiredArgsConstructor
public class ConsultationController {

    private final ConsultationService consultationService;

    @GetMapping
    public List<ConsultationDTO> findAll() {
        return consultationService.findAllConsultations();
    }

    @GetMapping(HISTORY)
    public List<ConsultationDTO> findAllPastConsultations(@PathVariable Long id, @RequestBody Patient patient) {
        if (consultationService.findAllConsultationsForAPatient(patient).isPresent())
            return consultationService.findAllConsultationsForAPatient(patient).get();
        else
            return new ArrayList<>();
    }

    @PostMapping
    public String create(@RequestBody ConsultationDTO consultationDTO) {
        Optional<ConsultationDTO> availability = consultationService.create(consultationDTO);
        if (availability.isPresent())
            return "The appointment was scheduled successfully!";
        else
            return "The doctor isn't available on that date and hour!";
    }

    @PostMapping(ENTITY)
    public ConsultationDTO edit(@PathVariable Long id, @RequestBody ConsultationDTO consultationDTO) {
        return consultationService.edit(id, consultationDTO);
    }

    @DeleteMapping(ENTITY)
    public void delete(@PathVariable Long id) {
        consultationService.delete(id);
    }

}
