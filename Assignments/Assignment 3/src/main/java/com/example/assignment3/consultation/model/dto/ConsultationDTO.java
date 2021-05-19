package com.example.assignment3.consultation.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsultationDTO {

    private Long id;
    private Long doctorId;
    private Long patientId;
    private String diagnosis;
    private String dateOfConsultation;
}
