package com.example.GestioneEventi.security.services;

import com.example.GestioneEventi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    private final static String USER_NOT_FOUND = "user with username %u not found";

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(String.format(USER_NOT_FOUND, username)));
    }

    public String signUpUser(UserDetailsImpl user){
        boolean userExists = userRepository.findByUsername((user.getUsername())).isPresent();

        if(userExists){
            try {
                throw new IllegalAccessException("user already token");
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);
        userRepository.save(user);
        return "funziona";
    }
}
