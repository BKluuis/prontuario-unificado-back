package com.castelar.prontuario.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.castelar.prontuario.dto.CredentialDTO;
import com.castelar.prontuario.dto.SignUpDTO;
import com.castelar.prontuario.dto.UserDTO;
import com.castelar.prontuario.model.User;
import com.castelar.prontuario.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody CredentialDTO credentialDTO){
        UserDTO user = userService.login(credentialDTO);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody SignUpDTO signUpDTO){
        UserDTO user = userService.register(signUpDTO);
        return ResponseEntity.created(URI.create("/users/" + user.getId())).body(user);
    }
}
