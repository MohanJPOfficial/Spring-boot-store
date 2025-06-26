package com.mohanjp.store.controllers;

import com.mohanjp.store.entity.UserEntity;
import com.mohanjp.store.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/users")
    public Iterable<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
}
