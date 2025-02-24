package com.example.GestioneEventi.repository;

import com.example.GestioneEventi.models.User;
import com.example.GestioneEventi.security.services.UserDetailsImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<UserDetailsImpl, Integer> {
    Optional<UserDetailsImpl> findByUsername(String username);

}
