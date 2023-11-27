package com.castelar.prontuario.service;

import java.nio.CharBuffer;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.castelar.prontuario.dto.authentication.CredentialDTO;
import com.castelar.prontuario.dto.authentication.SignUpDTO;
import com.castelar.prontuario.dto.authentication.UserDTO;
import com.castelar.prontuario.exception.AppException;
import com.castelar.prontuario.mapper.IUserMapper;
import com.castelar.prontuario.model.Role;
import com.castelar.prontuario.model.User;
import com.castelar.prontuario.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final IUserMapper userMapper;

    /**
     * Realiza o login do usuário, pesquisa no banco de dados se o login fornecido existe, se existe compara as senhas
     * @param credentialDTO Credenciais contendo login e senha
     * @return User do usuário encontrado
     */
    public User login(CredentialDTO credentialDTO) throws AppException{
        User user = userRepository.findByLogin(credentialDTO.login()).orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        
        if(passwordEncoder.matches(CharBuffer.wrap(credentialDTO.password()), user.getPassword())){
            // return userMapper.toDTO(user);
            return user;
        }
        throw new AppException("Unknown user", HttpStatus.BAD_REQUEST);
    }

    /**
     * Realiza o cadastro do usuário, pesquisa no banco de dados se o login fornecido existe, se existe 
     * @param signUpDTO Informações de cadastro necessárias para criar um novo usuário
     * @return User do usuário criado
     */
    public User register(SignUpDTO signUpDTO) throws AppException{
        Optional<User> oUser = userRepository.findByLogin(signUpDTO.login());

        if(oUser.isPresent()){
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }

        User user = userMapper.fromSignUpDTO(signUpDTO);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(signUpDTO.password())));
        user.setRole(Role.PATIENT);
        User savedUser = userRepository.save(user);
        // return userMapper.toDTO(user);
        return savedUser;
    }

    /**
     * TODO: Forçar logout no usuário, o objeto Authentication no SecurityContextHolder não é atualizado após a atualização do usuário
     * @param login
     * @param newRole
     * @return
     */
    public User updatePermissions(String login, Role newRole){
        User user = userRepository.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setRole(newRole);
        User savedUser = userRepository.save(user);

        return savedUser;
    }

    public User getLoggedUser(){
        return (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
