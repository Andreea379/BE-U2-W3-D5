package com.example.GestioneEventi.controller;

import com.example.GestioneEventi.payload.request.RegistrationRequest;
import com.example.GestioneEventi.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")


public class RegistrationController {
    @Autowired
    RegistrationService registrationService;

    @PostMapping("/newUser")
    public String register(@RequestBody RegistrationRequest request){
        return registrationService.save(request);
    }
}
