package com.castelar.prontuario.dto.authentication;

import com.castelar.prontuario.model.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    // private Long id;
    private String firstName;
    private String lastName;
    private String login;
    private Role role;
    private String token;
}
