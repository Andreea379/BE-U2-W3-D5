package com.example.GestioneEventi.controller;

import com.example.GestioneEventi.exceptions.UnauthorizedAccessException;
import com.example.GestioneEventi.models.User;
import com.example.GestioneEventi.payload.dto.NewEventDTO;
import com.example.GestioneEventi.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    EventService eventService;

    @PostMapping("/newEvent")
    public ResponseEntity<Integer> createEvent(@RequestBody NewEventDTO eventDTO, User user) throws UnauthorizedAccessException {
        Integer eventId = eventService.createEvent(eventDTO, user);
        return ResponseEntity.ok(eventId);
    }
    @GetMapping("/{id}")
    public ResponseEntity<NewEventDTO> findEventById(@PathVariable int id) {
        NewEventDTO eventDTO = eventService.findEventById(id);
        return ResponseEntity.ok(eventDTO);
    }
}
