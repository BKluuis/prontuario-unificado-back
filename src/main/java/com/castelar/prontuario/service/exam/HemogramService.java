package com.castelar.prontuario.service.exam;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.castelar.prontuario.dto.exam.HemogramDTO;
import com.castelar.prontuario.model.exam.Hemogram;
import com.castelar.prontuario.repository.exam.HemogramRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HemogramService implements HemogramServiceInterface{
    private final HemogramRepository hemogramRepository; 

    @Override
    public void create(HemogramDTO hemogram) {
        // if(hemogramRepository.existsById(hemogram.getId())){

        // }
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public void update(HemogramDTO hemogram, long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public void get(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }
}
