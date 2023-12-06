package com.castelar.prontuario.dto.authentication;

/** DTO para recebimento de credenciais de login do usuário via endpoint */
public record CredentialDTO(String login, char[] password) {

}
