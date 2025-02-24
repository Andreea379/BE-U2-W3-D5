package com.example.GestioneEventi.payload.request;

import lombok.*;

@Getter
@NoArgsConstructor(force = true)
@ToString
public class RegistrationRequest {
    private final String name;
    private final String surname;
    private final String email;
    private final String password;

    public RegistrationRequest(String name, String email, String surname, String password) {
        this.name = name;
        this.email = email;
        this.surname = surname;
        this.password = password;
    }
}
