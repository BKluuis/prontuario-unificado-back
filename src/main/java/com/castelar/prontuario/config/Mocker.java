package com.castelar.prontuario.config;

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
        ErythogramDTO er = new ErythogramDTO(1, 2, 3, 4, 5, 6, 7);
        LeukogramDTO lek = new LeukogramDTO(8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18);
        ThrombogramDTO tho = new ThrombogramDTO(19, 20, 21, 22);
        Hemogram hem = hMapper.fromDTO(new HemogramDTO(er, lek, tho));
        
        SignUpDTO admin_sign = new SignUpDTO("", "", "admin", "123".toCharArray());
        SignUpDTO employee_sign = new SignUpDTO("Josh", "Cabrero", "employee", "123".toCharArray());

        User admin = uService.register(admin_sign);
        User josh = uService.register(employee_sign);

        uService.updatePermissions(admin.getLogin(), Role.ADMIN);
        uService.updatePermissions(josh.getLogin(), Role.PROFESSIONAL);

        hService.addHemogramToUser(josh.getLogin(), hem);
    }
    
}
