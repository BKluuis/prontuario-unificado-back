package com.castelar.prontuario.service.exam;

import com.castelar.prontuario.model.exam.Hemogram;

public interface IHemogramService {
    public Hemogram create(Hemogram hemogram);
    public void update(Hemogram hemogram, Long id);
    public void delete(Long id);
    public Hemogram findById(Long id);
}
