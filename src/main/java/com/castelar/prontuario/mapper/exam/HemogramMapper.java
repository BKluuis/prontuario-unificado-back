package com.castelar.prontuario.mapper.exam;

import org.mapstruct.Mapper;

import com.castelar.prontuario.dto.exam.HemogramDTO;
import com.castelar.prontuario.model.exam.Hemogram;

@Mapper(componentModel = "spring")
public interface HemogramMapper {    
    HemogramDTO toDTO(Hemogram hemogram);
}
