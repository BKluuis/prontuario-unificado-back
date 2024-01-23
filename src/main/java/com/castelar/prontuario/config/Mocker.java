package com.castelar.prontuario.config;

import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.castelar.prontuario.dto.authentication.SignUpDTO;
import com.castelar.prontuario.dto.exam.ErythogramDTO;
import com.castelar.prontuario.dto.exam.HemogramDTO;
import com.castelar.prontuario.dto.exam.LeukogramDTO;
import com.castelar.prontuario.dto.exam.ThrombogramDTO;
import com.castelar.prontuario.mapper.exam.IHemogramMapper;
import com.castelar.prontuario.model.Role;
import com.castelar.prontuario.model.User;
import com.castelar.prontuario.model.exam.Hemogram;
import com.castelar.prontuario.service.UserService;
import com.castelar.prontuario.service.exam.HemogramService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class Mocker implements CommandLineRunner {
    private final HemogramService hService;
    private final IHemogramMapper hMapper;

    private final UserService uService;

    @Override
    public void run(String... args) throws Exception {
        SignUpDTO admin_sign = new SignUpDTO("adm", "castelar", "admin", "123".toCharArray());
        SignUpDTO pat1_sign = new SignUpDTO("Josh", "Cabrero", "josh_cabrero", "pat1".toCharArray());
        SignUpDTO pat2_sign = new SignUpDTO("Carla", "Pereira", "carla_pereira", "pat2".toCharArray());
        SignUpDTO prof1_sign = new SignUpDTO("Alice", "Silva", "alice_silva", "Pa$$w0rd1".toCharArray());
        SignUpDTO prof2_sign = new SignUpDTO("Bruno", "Oliveira", "bruno_oliveira", "Secr3tP@ss".toCharArray());


        User admin_user = uService.register(admin_sign);
        User pat1_user = uService.register(pat1_sign);
        User pat2_user = uService.register(pat2_sign);
        User prof1_user = uService.register(prof1_sign);
        User prof2_user = uService.register(prof2_sign);

        uService.updatePermissions(admin_user.getLogin(), Role.ADMIN);
        uService.updatePermissions(pat1_user.getLogin(), Role.PATIENT);
        uService.updatePermissions(pat2_user.getLogin(), Role.PATIENT);
        uService.updatePermissions(prof1_user.getLogin(), Role.PROFESSIONAL);
        uService.updatePermissions(prof2_user.getLogin(), Role.PROFESSIONAL);

        Hemogram hem1 = generateHemogram();
        Hemogram hem2 = generateHemogram();
        Hemogram hem3 = generateHemogram();
        Hemogram hem4 = generateHemogram();

        hService.addHemogramToUser(pat1_user.getLogin(), prof1_user.getLogin(), hem1);
        hService.addHemogramToUser(pat1_user.getLogin(), prof2_user.getLogin(), hem2);
        hService.addHemogramToUser(pat2_user.getLogin(), prof1_user.getLogin(), hem3);
        hService.addHemogramToUser(pat2_user.getLogin(), prof2_user.getLogin(), hem4);
    }
 
    
    private Integer generateRandomNumber(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    private Hemogram generateHemogram(){
        ErythogramDTO er = new ErythogramDTO(
            generateRandomNumber(1, 200),
            generateRandomNumber(1, 200),
            generateRandomNumber(1, 200),
            generateRandomNumber(1, 200),
            generateRandomNumber(1, 200),
            generateRandomNumber(1, 200),
            generateRandomNumber(1, 200)
        );
        
        LeukogramDTO lek = new LeukogramDTO(
            generateRandomNumber(1, 200),
            generateRandomNumber(1, 200),
            generateRandomNumber(1, 200),
            generateRandomNumber(1, 200),
            generateRandomNumber(1, 200),
            generateRandomNumber(1, 200),
            generateRandomNumber(1, 200),
            generateRandomNumber(1, 200),
            generateRandomNumber(1, 200),
            generateRandomNumber(1, 200),
            generateRandomNumber(1, 200)
        );
        
        ThrombogramDTO tho = new ThrombogramDTO(
            generateRandomNumber(1, 200),
            generateRandomNumber(1, 200),
            generateRandomNumber(1, 200),
            generateRandomNumber(1, 200)
        );

        return hMapper.fromDTO(new HemogramDTO(er, lek, tho, "Example comment"));
    }
}
