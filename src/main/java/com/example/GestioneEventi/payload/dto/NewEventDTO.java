package com.example.GestioneEventi.payload.dto;

import com.example.GestioneEventi.models.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
@Data
public class NewEventDTO {
    @NotNull(message = "Title field is obligatory")
    @NotBlank(message = "Title field could not be empty")
    @Size(min = 3, max = 15, message = "Title lenght is between 3 and 15 charaters")
    private String title;
    @NotNull(message = "Destination field is obligatory")
    private String description;
    @NotNull(message = "Date field is obligatory")
    LocalDate date;
    @NotNull(message = "Place field is obligatory")
    private String place;
    @NotNull(message = "PlacesAvailable field is obligatory")
    private int placesAvailable;

    private User user;
}
