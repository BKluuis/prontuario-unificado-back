package com.castelar.prontuario.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.castelar.prontuario.dto.exam.HemogramDTO;
import com.castelar.prontuario.mapper.exam.IHemogramMapper;
import com.castelar.prontuario.model.exam.Hemogram;
import com.castelar.prontuario.service.exam.IHemogramService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/hemogram")
@RequiredArgsConstructor
public class HemogramController {
    private final IHemogramService hemogramService;
    private final IHemogramMapper hemogramMapper;

    @PostMapping
    public ResponseEntity<Hemogram> postHemogram(HemogramDTO hemogram){
        Hemogram createdHemogram = hemogramService.create(hemogramMapper.fromDTO(hemogram));

        return new ResponseEntity<Hemogram>(createdHemogram, HttpStatus.CREATED);
    }
    /**
     * Apenas o usuário dono do hemograma e um profissional com ligação ao usuário pode requisitar um hemograma
     * @return
     */
    @GetMapping    
    public ResponseEntity<HemogramDTO> getHemogram(@RequestParam Long id){
        HemogramDTO hemo = hemogramMapper.toDTO(hemogramService.findById(id));

        return new ResponseEntity<HemogramDTO>(hemo, HttpStatus.OK);
    }
}
