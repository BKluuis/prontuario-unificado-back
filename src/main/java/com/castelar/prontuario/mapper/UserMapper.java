package com.castelar.prontuario.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.castelar.prontuario.dto.SignUpDTO;
import com.castelar.prontuario.dto.UserDTO;
import com.castelar.prontuario.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toUserDTO(User user);
    
    @Mapping(target = "password", ignore = true)
    User signUpDTOtoUser(SignUpDTO signUpDTO);
}
