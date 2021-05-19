package com.example.assignment3.consultation;

import com.example.assignment3.consultation.model.Consultation;
import com.example.assignment3.consultation.model.dto.ConsultationDTO;
import com.example.assignment3.patient.PatientRepository;
import com.example.assignment3.patient.model.Patient;
import com.example.assignment3.user.UserRepository;
import com.example.assignment3.user.model.User;
import com.example.assignment3.websockets.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsultationService {

    private final ConsultationRepository consultationRepository;
    private final ConsultationMapper consultationMapper;
    private final UserRepository userRepository;
    private final PatientRepository patientRepository;
    private final SimpMessageSendingOperations template;

    public Consultation findById(Long id) {
        return consultationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consultation with id = " + id + " not found!"));
    }

    public List<ConsultationDTO> findAllConsultations() {
        return consultationRepository.findAll().stream()
                .map(consultationMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<List<ConsultationDTO>> findAllConsultationsForAPatient(Patient patient) {
        if (consultationRepository.findAllByPatientId(patient).isPresent())
            return Optional.of(consultationRepository.findAllByPatientId(patient).get().stream()
                    .map(consultationMapper::toDTO)
                    .collect(Collectors.toList()));
        else
            return Optional.empty();
    }

    public Optional<ConsultationDTO> create(ConsultationDTO consultationDTO) {

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");
        LocalDateTime dateOfConsult = LocalDateTime.parse(consultationDTO.getDateOfConsultation(), format);

        User doctor =  userRepository.findById(consultationDTO.getDoctorId()).orElseThrow(() -> new EntityNotFoundException(
                        "Doctor with id = " + consultationDTO.getDoctorId() + " not found!"));

        if(!consultationRepository.existsConsultationByDateOfConsultationAndDoctorId(dateOfConsult, doctor)) {

            Patient patient = patientRepository.findById(consultationDTO.getPatientId())
                    .orElseThrow(() -> new EntityNotFoundException("Patient with id = "
                            + consultationDTO.getPatientId() + " not found!"));

            template.convertAndSend("/topic/notification" + doctor.getUsername(),
                    new Notification("You have a new appointment on "
                            + dateOfConsult + " with patient " + patient.getName()).getMessageForTheDoctor());

            return Optional.of(consultationMapper.toDTO(
                    consultationRepository.save(consultationMapper.fromDTO(consultationDTO))
            ));
        } else
            return Optional.empty();

    }

    public ConsultationDTO edit(Long id, ConsultationDTO consultationDTO) {
        Consultation actConsultation = findById(id);

        actConsultation.setDoctorId(userRepository.findById(consultationDTO.getDoctorId())
                .orElseThrow(() -> new EntityNotFoundException(
                "Doctor with id = " + consultationDTO.getDoctorId() + " not found!")));

        actConsultation.setPatientId(patientRepository.findById(consultationDTO.getPatientId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Patient with id = " + consultationDTO.getPatientId() + " not found!")));

        actConsultation.setDiagnosis(consultationDTO.getDiagnosis());

        return consultationMapper.toDTO(
                consultationRepository.save(actConsultation)
        );
    }

    public void delete(Long id) {
        consultationRepository.deleteById(id);
    }

}
