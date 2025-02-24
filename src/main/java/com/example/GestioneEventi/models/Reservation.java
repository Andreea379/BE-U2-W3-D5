package com.example.GestioneEventi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idReservation;

    @ManyToOne
    @JoinColumn(name="idEvent")
    private Event event;

    @ManyToOne
    @JoinColumn(name="id")
    private User user;

}
