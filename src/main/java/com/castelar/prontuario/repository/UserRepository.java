package com.castelar.prontuario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.castelar.prontuario.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByLogin(String login);
}
