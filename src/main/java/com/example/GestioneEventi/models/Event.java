package com.example.GestioneEventi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEvent;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    LocalDate date;
    @Column(nullable = false)
    private String place;
    @Column(nullable = false)
    private int placesAvailable;

    @ManyToOne
    @JoinColumn(name="id")
    private User user;
}
