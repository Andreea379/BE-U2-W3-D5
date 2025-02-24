package com.example.GestioneEventi.service;

import com.example.GestioneEventi.payload.request.RegistrationRequest;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    public String save(RegistrationRequest request) {
        return "it works";
    }
}
