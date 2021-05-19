package com.example.assignment3.patient.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String name;

    @Column(length = 8, nullable = false)
    private String identityCard;

    @Column(length = 13, nullable = false)
    private Long CNP;

    @Column(length = 10, nullable = false)
    private String dateOfBirth;

    @Column(length = 500, nullable = false)
    private String address;
}
