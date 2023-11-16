package com.castelar.prontuario.mapper;

import org.mapstruct.Mapper;

import com.castelar.prontuario.dto.UserDTO;
import com.castelar.prontuario.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toUserDTO(User user);
}
