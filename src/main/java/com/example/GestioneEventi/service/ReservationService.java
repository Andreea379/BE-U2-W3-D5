package com.example.GestioneEventi.service;

import com.example.GestioneEventi.models.Reservation;
import com.example.GestioneEventi.payload.dto.NewReservationDTO;
import com.example.GestioneEventi.repository.ReservationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {
    @Autowired
    ReservationRepository reservationRepository;

    public Page<NewReservationDTO> getAllReservations(Pageable page) {
        Page<Reservation> reservationsList = reservationRepository.findAll(page);
        List<NewReservationDTO> listDto = new ArrayList<>();

        for (Reservation reservation : reservationsList.getContent()) {
            NewReservationDTO dto = fromReservatioToReservationDTO(reservation);
            listDto.add(dto);
        }
        Page<NewReservationDTO> listPage = new PageImpl<>(listDto);
        return listPage;
    }

    public NewReservationDTO fromReservatioToReservationDTO(Reservation reservation) {
        NewReservationDTO reservationDTO = new NewReservationDTO();
        reservationDTO.setIdUser(reservation.getUser().getId());
        reservationDTO.getIdEvent(reservation.getEvent().getIdEvent());
        return reservationDTO;
    }
}
