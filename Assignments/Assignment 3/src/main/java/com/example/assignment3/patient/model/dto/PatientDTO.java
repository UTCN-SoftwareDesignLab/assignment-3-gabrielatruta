package com.example.assignment3.patient.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {

    private Long id;
    private String name;
    private String identityCard;
    private Long CNP;
    private String dateOfBirth;
    private String address;
}
