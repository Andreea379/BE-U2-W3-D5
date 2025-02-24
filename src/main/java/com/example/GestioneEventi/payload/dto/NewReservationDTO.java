package com.example.GestioneEventi.payload.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data

public class NewReservationDTO {
    @NotNull(message = "IdEvent field is obligatory")
    private int idEvent;
    @NotNull(message = "IdUser field is obligatory")
    private int idUser;

    public void getIdEvent(int idEvent) {
    }
}
