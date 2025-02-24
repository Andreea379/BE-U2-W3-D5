package com.example.GestioneEventi.payload.dto;

import com.example.GestioneEventi.models.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;

@Data
public class NewUserDTO {
    @NotNull(message = "Name field is obligatory")
    @NotBlank(message = "Name field could not be empty")
    @Size(min = 3, max = 15, message = "Name lenght is between 3 and 15 charaters")
    private String name;
    @NotNull(message = "Username field is obligatory")
    @NotBlank(message = "Username field could not be empty")
    @Size(min = 3, max = 15, message = "Username lenght is between 3 and 15 charaters")
    private String username;
    @NotNull(message = "Email field is obligatory")
    private String email;
    private String password;
    @NotNull(message = "User role field is obligatory")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
}
