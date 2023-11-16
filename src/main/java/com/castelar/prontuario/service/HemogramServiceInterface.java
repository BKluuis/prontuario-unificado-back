package com.castelar.prontuario.service;

import com.castelar.prontuario.dto.HemogramDTO;

public interface HemogramServiceInterface {
    public void create(HemogramDTO hemogram);
    public void update(HemogramDTO hemogram, long id);
    public void delete(long id);
    public void get(long id);
}
