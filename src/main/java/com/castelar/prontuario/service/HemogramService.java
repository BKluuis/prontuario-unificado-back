package com.castelar.prontuario.service;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.castelar.prontuario.dto.HemogramDTO;
import com.castelar.prontuario.model.examType.Hemogram;
import com.castelar.prontuario.repository.HemogramRepository;

@Service
public class HemogramService implements HemogramServiceInterface{
    private HemogramRepository hemogramRepository; 

    public HemogramService(HemogramRepository hemogramRepository){
        this.hemogramRepository = hemogramRepository;
    }

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
