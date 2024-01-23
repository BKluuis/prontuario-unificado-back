package com.castelar.prontuario.repository.exam;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.castelar.prontuario.model.exam.Hemogram;
import com.castelar.prontuario.model.User;


@Repository
public interface HemogramRepository extends JpaRepository<Hemogram, Long> {
    Optional<Hemogram> findByOwnerAndId(User owner, Long id);
    Optional<Hemogram> findByProfessionalAndId(User professional, Long id);
    List<Hemogram> findAllByOwner(User owner);

}