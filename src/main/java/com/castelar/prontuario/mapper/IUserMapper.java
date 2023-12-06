package com.castelar.prontuario.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.castelar.prontuario.dto.authentication.SignUpDTO;
import com.castelar.prontuario.dto.authentication.UserDTO;
import com.castelar.prontuario.model.User;

@Mapper(componentModel = "spring")
public interface IUserMapper {
    UserDTO toDTO(User user);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    User fromDTO(UserDTO userDTO);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "token", ignore = true)
    @Mapping(target = "password", ignore = true)
    User fromSignUpDTO(SignUpDTO signUpDTO);

}
