package com.castelar.prontuario.repository.exam;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.castelar.prontuario.model.exam.Hemogram;
import com.castelar.prontuario.model.User;


@Repository
public interface HemogramRepository extends JpaRepository<Hemogram, Long> {
    Optional<Hemogram> findByOwner(User owner);
}