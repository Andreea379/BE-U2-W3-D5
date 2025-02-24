package com.example.GestioneEventi.service;
import com.example.GestioneEventi.exceptions.BadRequestException;
import com.example.GestioneEventi.exceptions.NotFoundException;
import com.example.GestioneEventi.exceptions.UnauthorizedAccessException;
import com.example.GestioneEventi.models.Event;
import com.example.GestioneEventi.models.Reservation;
import com.example.GestioneEventi.models.User;
import com.example.GestioneEventi.models.UserRole;
import com.example.GestioneEventi.payload.dto.NewReservationDTO;
import com.example.GestioneEventi.payload.dto.NewUserDTO;
import com.example.GestioneEventi.repository.EventRepository;
import com.example.GestioneEventi.repository.ReservationRepository;
import com.example.GestioneEventi.repository.UserRepository;
import com.example.GestioneEventi.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    ReservationRepository reservationRepository;

    public NewUserDTO getUserByUsername(String username){
        Optional<UserDetailsImpl> user = userRepository.findByUsername(username);
        if(user.isPresent()){
            NewUserDTO userDTO = fromUserToUserDTO(user.get());
            return userDTO;
        }else{
            throw new NotFoundException("None user with username: " + username + " found!");
        }
    }

    public void updateUser(NewUserDTO userDTO, String username) throws UnauthorizedAccessException {
        Optional<UserDetailsImpl> foundUser = userRepository.findByUsername(username);
        if (foundUser.isPresent() && foundUser.get().getUserRole() == UserRole.ADMIN_ROLE) {
            if (foundUser.isPresent()) {
                UserDetailsImpl user = foundUser.get();
                user.setName(userDTO.getName());
                user.setUsername(userDTO.getUsername());
                user.setEmail(userDTO.getEmail());
                user.setPassword(userDTO.getPassword());
                user.setUserRole(userDTO.getUserRole());
                userRepository.save(user);
            } else {
                throw new NotFoundException("Error to update the inserted user. User not found!");
            }
        }else{
            throw new UnauthorizedAccessException("You must be an admin to update a user.");
        }
    }

    public void createNewReservation(NewReservationDTO reservationDTO) throws BadRequestException {
        Optional<User> foundUser = userRepository.findByUsername(reservationDTO.getIdUser());
        Optional<Event> foundEvent = eventRepository.findById(reservationDTO.getIdEvent());

        if(foundUser.isPresent() && foundEvent.isPresent()){
            User user = foundUser.get();
            Event event = foundEvent.get();

            List<Reservation> reservationsWithSameUser = reservationRepository.findByUser(user);

            if (!reservationsWithSameUser.isEmpty()) {
                throw new BadRequestException("Il dipendente selezionato ha gia una prenotazione per questa data");
            } else {
                Reservation reservation = new Reservation();
                reservation.setUser(user);
                reservation.setEvent(event);
                reservationRepository.save(reservation);
            }
        } else {
            throw new NotFoundException(" L'id del viaggio o del dipendente non è stato trovato");
        }
    }
}

//travaso
public User fromUserDTOtoUser(NewUserDTO userDTO) {
    User user = new User();
    user.setName(userDTO.getName());
    user.setUsername(userDTO.getUsername());
    user.setEmail(userDTO.getEmail());
    user.setPassword(userDTO.getPassword());
    user.setUserRole(userDTO.getUserRole());
    return user;
}
public NewUserDTO fromUserToUserDTO(UserDetailsImpl user){
    NewUserDTO userDTO = new NewUserDTO();
    userDTO.setName(user.getName());
    userDTO.setUsername(user.getUsername());
    userDTO.setEmail(user.getEmail());
    userDTO.setPassword(user.getPassword());
    userDTO.setUserRole(user.getUserRole());

    return userDTO;
}
}

