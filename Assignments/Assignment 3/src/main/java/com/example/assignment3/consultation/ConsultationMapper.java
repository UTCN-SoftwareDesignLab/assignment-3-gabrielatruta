package com.example.assignment3.consultation;

import com.example.assignment3.consultation.model.Consultation;
import com.example.assignment3.consultation.model.dto.ConsultationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ConsultationMapper {

    @Mappings({
            @Mapping(target = "patientId", source = "consultation.patientId.id"),
            @Mapping(target = "doctorId", source = "consultation.doctorId.id"),
            @Mapping(target="dateOfConsultation", source = "consultation.dateOfConsultation", dateFormat = "yyyy-MM-dd HH")
    })
    ConsultationDTO toDTO(Consultation consultation);

    @Mappings({
            @Mapping(target = "patientId.id", source = "consultationDTO.patientId"),
            @Mapping(target = "doctorId.id", source = "consultationDTO.doctorId"),
            @Mapping(target="dateOfConsultation", source = "consultationDTO.dateOfConsultation", dateFormat = "yyyy-MM-dd HH")
    })
    Consultation fromDTO(ConsultationDTO consultationDTO);
}
