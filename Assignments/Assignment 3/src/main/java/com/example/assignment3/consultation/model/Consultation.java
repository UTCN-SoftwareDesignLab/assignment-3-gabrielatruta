package com.example.assignment3.consultation.model;

import com.example.assignment3.patient.model.Patient;
import com.example.assignment3.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctorID")
    private User doctorId;

    @ManyToOne
    @JoinColumn(name = "patientID")
    private Patient patientId;

    @Column(length = 1500, nullable = false)
    private String diagnosis;

    @Column(nullable = false)
    private LocalDateTime dateOfConsultation;

}
