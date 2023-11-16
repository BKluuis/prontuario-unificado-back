package com.castelar.prontuario.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.castelar.prontuario.dto.exam.ErythogramDTO;
import com.castelar.prontuario.dto.exam.HemogramDTO;
import com.castelar.prontuario.dto.exam.LeukogramDTO;
import com.castelar.prontuario.dto.exam.ThrombogramDTO;
import com.castelar.prontuario.model.exam.Erythogram;
import com.castelar.prontuario.model.exam.Hemogram;
import com.castelar.prontuario.model.exam.Leukogram;
import com.castelar.prontuario.model.exam.Thrombogram;
import com.castelar.prontuario.service.exam.HemogramService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/hemogram")
@RequiredArgsConstructor
public class HemogramController {
    private final HemogramService hemogramService;

    /**
     * Apenas o usuário dono do hemograma e um profissional com ligação ao usuário pode requisitar um hemogram
     * @return
     */
    @GetMapping    
    public ResponseEntity<Hemogram> getHemogram(/* User, id */){
        Erythogram er = new Erythogram(0L, 1, 2, 3, 4, 5, 6, 7);
        Leukogram lek = new Leukogram(0L, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18);
        Thrombogram tho = new Thrombogram(0L, 19, 20, 21, 22);
        Hemogram test = new Hemogram(0L, er, lek, tho);

        return new ResponseEntity<Hemogram>(test, HttpStatus.OK);
    }
}
