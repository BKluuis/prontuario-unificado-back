package com.castelar.prontuario.service.exam;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.castelar.prontuario.dto.exam.HemogramDTO;
import com.castelar.prontuario.mapper.IUserMapper;
import com.castelar.prontuario.mapper.exam.IHemogramMapper;
import com.castelar.prontuario.model.User;
import com.castelar.prontuario.model.exam.Hemogram;
import com.castelar.prontuario.repository.exam.HemogramRepository;
import com.castelar.prontuario.service.UserService;
import com.castelar.prontuario.exception.AppException;
import com.castelar.prontuario.dto.authentication.UserDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HemogramService implements IHemogramService {
    private final HemogramRepository hemogramRepository; 
    private final UserService userService;
    private final IUserMapper userMapper;
    
    @Override
    public Hemogram create(Hemogram hemogram) {
        // UserDTO user = userService.getLoggedUser();
        
        Hemogram savedHemogram = hemogramRepository.save(hemogram);
        
        return savedHemogram;
    }

    @Override
    public void update(Hemogram hemogram, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Hemogram findById(Long id) {
        // UserDTO user = userService.getLoggedUser();

        Hemogram hemogram = hemogramRepository.findById(id)
                                              .orElseThrow(() -> new AppException("No hemogram found with the supplied ID", HttpStatus.NOT_FOUND));

        return hemogram;
    }
}
