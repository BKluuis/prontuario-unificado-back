package com.castelar.prontuario.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.castelar.prontuario.dto.ErythogramDTO;
import com.castelar.prontuario.dto.HemogramDTO;
import com.castelar.prontuario.dto.LeukogramDTO;
import com.castelar.prontuario.dto.ThrombogramDTO;
import com.castelar.prontuario.model.examType.Erythogram;
import com.castelar.prontuario.model.examType.Hemogram;
import com.castelar.prontuario.model.examType.Leukogram;
import com.castelar.prontuario.model.examType.Thrombogram;
import com.castelar.prontuario.service.HemogramService;

@RestController
@RequestMapping("/api/hemogram")
public class HemogramController {
    private HemogramService hemogramService;

    public HemogramController(HemogramService hemogramService){
        this.hemogramService = hemogramService;
    }

    /**
     * Apenas o usuário dono do hemograma e um profissional com ligação ao usuário pode requisitar um hemogram
     * @return
     */
    @GetMapping    
    public ResponseEntity<Hemogram> getHemogram(/* User, id */){
        Erythogram er = new Erythogram(0, 1, 2, 3, 4, 5, 6, 7);
        Leukogram lek = new Leukogram(0, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18);
        Thrombogram tho = new Thrombogram(0, 19, 20, 21, 22);
        Hemogram test = new Hemogram(0, er, lek, tho);

        return new ResponseEntity<Hemogram>(test, HttpStatus.OK);
    }
}
