package com.castelar.prontuario.mapper.exam;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.castelar.prontuario.dto.exam.HemogramDTO;
import com.castelar.prontuario.model.exam.Hemogram;

@Mapper(componentModel = "spring")
public interface IHemogramMapper {    
    HemogramDTO toDTO(Hemogram hemogram);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "professional", ignore = true)
    @Mapping(target = "erythogram.id", ignore = true)
    @Mapping(target = "thrombogram.id", ignore = true)
    @Mapping(target = "leukogram.id", ignore = true)
    Hemogram fromDTO(HemogramDTO dto);
}
