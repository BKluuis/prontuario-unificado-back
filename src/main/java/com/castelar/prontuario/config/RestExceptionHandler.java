package com.castelar.prontuario.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.castelar.prontuario.dto.ErrorDTO;
import com.castelar.prontuario.exception.AppException;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(value = { AppException.class })
    @ResponseBody
    public ResponseEntity<ErrorDTO> handleException(AppException e){
        return ResponseEntity.status(e.getHttpStatus()).body(new ErrorDTO(e.getMessage()));
    }
}
