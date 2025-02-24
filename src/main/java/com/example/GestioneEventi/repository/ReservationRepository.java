package com.example.GestioneEventi.repository;

import com.example.GestioneEventi.models.Reservation;
import com.example.GestioneEventi.models.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
@Repository
public interface ReservationRepository {
    List<Reservation> findByUser(User user);

    Page<Reservation> findAll(Pageable page);

    void save(Reservation reservation);
}
