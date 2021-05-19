package com.example.assignment3.consultation;


import com.example.assignment3.consultation.model.Consultation;
import com.example.assignment3.patient.model.Patient;
import com.example.assignment3.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {

    Optional<Consultation> findById(Long id);

    Boolean existsConsultationByDateOfConsultationAndDoctorId(LocalDateTime dateOfConsultation, User doctor);

    Optional<List<Consultation>> findAllByPatientId(Patient patient);
}
