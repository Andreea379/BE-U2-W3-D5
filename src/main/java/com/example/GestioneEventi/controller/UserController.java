package com.example.GestioneEventi.controller;

import com.example.GestioneEventi.payload.dto.NewReservationDTO;
import com.example.GestioneEventi.payload.dto.NewUserDTO;
import com.example.GestioneEventi.service.;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/username")
    public NewUserDTO getUserByUsername(@PathVariable String username){
        return userService.getUserByUsername(username);
    }

    @PutMapping("/updateUser/username")
    public ResponseEntity<?> updateUser(@RequestBody @Validated NewUserDTO userDTO, BindingResult validation, @PathVariable String username){
        if (validation.hasErrors()) {
            String errorMessage = "VALIDATION ERROR \n";

            for (ObjectError error : validation.getAllErrors()) {
                errorMessage += error.getDefaultMessage() + "\n";
            }
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        } else {
            userService.updateUser(userDTO, username);
            return new ResponseEntity<>("The user was updated successfully!", HttpStatus.OK);
        }
    }

    @PostMapping("newReservation")
    public ResponseEntity<?> createNewReservation(@RequestBody @Validated NewReservationDTO reservationDTO, BindingResult validation){
        if (validation.hasErrors()) {
            String errorMessage = "VALIDATION ERROR \n";

            for (ObjectError error : validation.getAllErrors()) {
                errorMessage += error.getDefaultMessage() + "\n";
            }
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        } else {
            userService.createNewReservation(reservationDTO);
            return new ResponseEntity<>("The reservation was updated successfully!", HttpStatus.OK);
        }

    }
    }
}
