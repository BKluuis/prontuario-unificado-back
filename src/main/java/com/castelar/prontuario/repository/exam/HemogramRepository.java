package com.castelar.prontuario.repository.exam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.castelar.prontuario.model.exam.Hemogram;

@Repository
public interface HemogramRepository extends JpaRepository<Hemogram, Long> {

}