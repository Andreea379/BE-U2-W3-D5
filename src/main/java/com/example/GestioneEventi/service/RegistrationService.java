package com.example.GestioneEventi.service;

import com.example.GestioneEventi.models.UserRole;
import com.example.GestioneEventi.payload.request.RegistrationRequest;
import com.example.GestioneEventi.repository.UserRepository;
import com.example.GestioneEventi.security.services.UserDetailsImpl;
import com.example.GestioneEventi.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
@Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    public String save(RegistrationRequest request) {
        return userDetailsServiceImpl.signUpUser(new UserDetailsImpl(
                request.getName(),
                request.getUsername(),
                request.getEmail(),
                request.getPassword()));
    }
}
