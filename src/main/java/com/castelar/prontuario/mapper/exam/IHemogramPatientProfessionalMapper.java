package com.castelar.prontuario.mapper.exam;

import com.castelar.prontuario.dto.exam.HemogramDTO;
import com.castelar.prontuario.dto.exam.HemogramPatientProfessionalDTO;
import com.castelar.prontuario.model.exam.Hemogram;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IHemogramPatientProfessionalMapper {
    @Mapping(source = "owner.firstName", target = "patientName")
    @Mapping(source = "professional.firstName", target = "professionalName")
    HemogramPatientProfessionalDTO toDTO(Hemogram hemogram);


    @Mapping(source = "owner.firstName", target = "patientName")
    @Mapping(source = "professional.firstName", target = "professionalName")
    @Mapping(source = "date", target = "date")
    List<HemogramPatientProfessionalDTO> toDTOS(List<Hemogram> hemograms);


}
