package com.example.GestioneEventi.payload.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(force = true)
@ToString
public class RegistrationRequest {
    private final String name;
    private final String username;
    private final String email;
    private final String password;

    public RegistrationRequest(String name, String email, String username, String password) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
