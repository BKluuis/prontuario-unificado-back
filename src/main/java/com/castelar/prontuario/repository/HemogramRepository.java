package com.castelar.prontuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.castelar.prontuario.model.examType.Hemogram;

public interface HemogramRepository extends JpaRepository<Hemogram, Long> {

}