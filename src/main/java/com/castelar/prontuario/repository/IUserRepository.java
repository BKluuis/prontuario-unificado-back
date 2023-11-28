package com.castelar.prontuario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.castelar.prontuario.model.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long>{
    Optional<User> findByLogin(String login);
}
