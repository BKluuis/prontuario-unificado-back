package com.castelar.prontuario.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.castelar.prontuario.config.UserAuthProvider;
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
    private final UserAuthProvider userAuthProvider;

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody CredentialDTO credentialDTO){
        UserDTO user = userService.login(credentialDTO);

        user.setToken(userAuthProvider.generateToken(user));

        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody SignUpDTO signUpDTO){
        UserDTO user = userService.register(signUpDTO);

        user.setToken(userAuthProvider.generateToken(user));

        return ResponseEntity.created(URI.create("/users/" + user.getId())).body(user);
    }
}
