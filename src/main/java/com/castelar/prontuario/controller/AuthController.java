package com.castelar.prontuario.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.castelar.prontuario.config.UserAuthProvider;
import com.castelar.prontuario.dto.authentication.CredentialDTO;
import com.castelar.prontuario.dto.authentication.SignUpDTO;
import com.castelar.prontuario.dto.authentication.UserDTO;
import com.castelar.prontuario.mapper.IUserMapper;
import com.castelar.prontuario.model.Role;
import com.castelar.prontuario.model.User;
import com.castelar.prontuario.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final UserAuthProvider userAuthProvider;
    private final IUserMapper userMapper;

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody CredentialDTO credentialDTO){
        User user = userService.login(credentialDTO);

        user.setToken(userAuthProvider.generateToken(user));

        return ResponseEntity.ok(userMapper.toDTO(user));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody SignUpDTO signUpDTO){
        User user = userService.register(signUpDTO);

        user.setToken(userAuthProvider.generateToken(user));

        return ResponseEntity.created(URI.create("/users/" + user.getId())).body(userMapper.toDTO(user));
    }

     @PostMapping("/update-user")
    public ResponseEntity<UserDTO> update(@RequestParam String login, @RequestParam Role role){
        User updatedUser = userService.updatePermissions(login, role);

        updatedUser.setToken(userAuthProvider.generateToken(updatedUser));

        return ResponseEntity.created(URI.create("/users/" + updatedUser.getId())).body(userMapper.toDTO(updatedUser));
    }
}
