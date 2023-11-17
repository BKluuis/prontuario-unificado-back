package com.castelar.prontuario.service;

import java.nio.CharBuffer;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.castelar.prontuario.dto.CredentialDTO;
import com.castelar.prontuario.dto.SignUpDTO;
import com.castelar.prontuario.dto.UserDTO;
import com.castelar.prontuario.exception.AppException;
import com.castelar.prontuario.mapper.UserMapper;
import com.castelar.prontuario.model.User;
import com.castelar.prontuario.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserDTO login(CredentialDTO credentialDTO){
        User user = userRepository.findByLogin(credentialDTO.login()).orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        
        if(passwordEncoder.matches(CharBuffer.wrap(credentialDTO.password()), user.getPassword())){
            return userMapper.toUserDTO(user);
        }
        throw new AppException("Unknown user", HttpStatus.BAD_REQUEST);
    }

    public UserDTO register(SignUpDTO signUpDTO) {
        Optional<User> oUser = userRepository.findByLogin(signUpDTO.login());

        if(oUser.isPresent()){
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }

        User user = userMapper.signUpDTOtoUser(signUpDTO);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(signUpDTO.password())));
        User savedUser = userRepository.save(user);
        return userMapper.toUserDTO(savedUser);
    }
}
