package com.castelar.prontuario.dto.authentication;

/** DTO para recebimento de credenciais de cadastro do usuário via endpoint */
public record SignUpDTO(String firstName, String lastName, String login, char[] password) {
    
}
