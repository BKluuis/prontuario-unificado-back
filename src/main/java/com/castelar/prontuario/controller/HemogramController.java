package com.castelar.prontuario.controller;

import com.castelar.prontuario.dto.exam.HemogramPatientProfessionalDTO;
import com.castelar.prontuario.mapper.exam.IHemogramPatientProfessionalMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.castelar.prontuario.dto.exam.HemogramDTO;
import com.castelar.prontuario.mapper.exam.IHemogramMapper;
import com.castelar.prontuario.model.User;
import com.castelar.prontuario.service.exam.IHemogramService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/hemogram")
@RequiredArgsConstructor
public class HemogramController {
    private final IHemogramService hemogramService;
    private final IHemogramMapper hemogramMapper;
    private final IHemogramPatientProfessionalMapper hemogramPatientProfessionalMapper;

    @PostMapping
    public ResponseEntity<HemogramDTO> postHemogram(@RequestParam String patientLogin,@RequestBody HemogramDTO hemogram, @AuthenticationPrincipal User user){
        System.out.println("Este usuário requisitou a inclusão de um hemograma " + user);

        HemogramDTO createdHemogram = hemogramMapper.toDTO(
                                        hemogramService.addHemogramToUser(patientLogin,user.getLogin() ,hemogramMapper.fromDTO(hemogram))
                                    );

        return new ResponseEntity<HemogramDTO>(createdHemogram, HttpStatus.CREATED);
    }
    /**
     * Apenas o usuário dono do hemograma ou um profissional com ligação ao usuário pode requisitar um hemograma
     * @return
     */
    @GetMapping    
    public ResponseEntity<HemogramDTO> getHemogram(@RequestParam Long id, @AuthenticationPrincipal User user){
                System.out.println("Este usuário requisitou um hemograma " + user);
        HemogramDTO hemo = hemogramMapper.toDTO(hemogramService.findByIdAndUser(user.getLogin() , id));

        return new ResponseEntity<HemogramDTO>(hemo, HttpStatus.OK);
    }

    @GetMapping("/get-hemograms")
    public ResponseEntity<List<HemogramPatientProfessionalDTO>> getPatientHemograms(@RequestParam String patientLogin){
        List<HemogramPatientProfessionalDTO> userHemograms = hemogramPatientProfessionalMapper.toDTOS(hemogramService.findUserHemograms(patientLogin));

        return new ResponseEntity<List<HemogramPatientProfessionalDTO>>(userHemograms, HttpStatus.OK);
    }
}
