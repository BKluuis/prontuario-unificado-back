package com.castelar.prontuario.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.castelar.prontuario.dto.ErrorDTO;
import com.castelar.prontuario.exception.AppException;

/**
 * Controlador responsável por tomar conta de exceções REST
 * TODO: Analisar exceções específicas lançadas pelos métodos na aplicação e adicionar aqui
 */
@ControllerAdvice
public class RestExceptionHandler {
    /**
     * Método responsável por tratar exceções do tipo "AppException"
     * @param e Exceção do tipo AppException
     * @return Uma resposta HTTP com o código HTTP de AppException e com corpo de resposta com a mensagem de AppException
     */
    @ExceptionHandler(value = { AppException.class })
    @ResponseBody
    public ResponseEntity<ErrorDTO> handleException(AppException e){
        return ResponseEntity.status(e.getHttpStatus()).body(new ErrorDTO(e.getMessage()));
    }
}
