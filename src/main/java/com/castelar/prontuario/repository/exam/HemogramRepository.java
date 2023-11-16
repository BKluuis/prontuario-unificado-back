package com.castelar.prontuario.repository.exam;

import org.springframework.data.jpa.repository.JpaRepository;

import com.castelar.prontuario.model.exam.Hemogram;

public interface HemogramRepository extends JpaRepository<Hemogram, Long> {

}