package com.example.GestioneEventi.service;

import com.example.GestioneEventi.exceptions.NotFoundException;
import com.example.GestioneEventi.exceptions.UnauthorizedAccessException;
import com.example.GestioneEventi.models.Event;
import com.example.GestioneEventi.models.User;
import com.example.GestioneEventi.models.UserRole;
import com.example.GestioneEventi.payload.dto.NewEventDTO;
import com.example.GestioneEventi.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventService {
    @Autowired
    EventRepository eventRepository;

    public Integer createEvent(NewEventDTO eventDTO, User user) throws UnauthorizedAccessException {
        if(user.getUserRole() != UserRole.ADMIN_ROLE && user.getUserRole() != UserRole.MANAGER_ROLE){
            throw new UnauthorizedAccessException("You must be an admin to create an event");
        }
        Event insertedEvent = fromEventDTOtoEvent(eventDTO);
        Event savedEvent = eventRepository.save(insertedEvent);
        return savedEvent.getIdEvent();
    }

    public NewEventDTO findEventById(int id) {
        Optional<Event> event = eventRepository.findById(id);

        if (event.isPresent()) {
            NewEventDTO viaggiDto = fromEventToEventDTO(event.get());
            return viaggiDto;
        } else {
            throw new NotFoundException("None event with id: " + id + " was found!");
        }
    }


    //travaso
    public Event fromEventDTOtoEvent(NewEventDTO eventDTO) {
        Event event = new Event();
        event.setTitle(eventDTO.getTitle());
        event.setDescription(eventDTO.getDescription());
        event.setDate(eventDTO.getDate());
        event.setPlace(eventDTO.getPlace());
        event.setPlacesAvailable(event.getPlacesAvailable());
        return event;
    }
    public NewEventDTO fromEventToEventDTO(Event event){
        NewEventDTO eventDTO = new NewEventDTO();
        eventDTO.setTitle(event.getTitle());
        eventDTO.setDescription(event.getDescription());
        eventDTO.setDate(eventDTO.getDate());
        eventDTO.setPlace(event.getPlace());
        eventDTO.setPlacesAvailable(eventDTO.getPlacesAvailable());
        return eventDTO;
    }
}
