package com.mohanjp.store.homeWork_UserRegistration.presentation;

import com.mohanjp.store.homeWork_UserRegistration.domain.model.User;
import com.mohanjp.store.homeWork_UserRegistration.domain.notification.NotificationService;
import com.mohanjp.store.homeWork_UserRegistration.domain.repository.UserRepository;

public class UserService {

    private final NotificationService notificationService;
    private final UserRepository userRepository;

    public UserService(NotificationService notificationService, UserRepository userRepository) {
        this.notificationService = notificationService;
        this.userRepository = userRepository;
    }

    public void registerUser(User user) {

        if(userRepository.findByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("User with email " + user.getEmail() + " already exists");
        }

        userRepository.save(user);
        notificationService.send("New user " + user.getName() + " is created", user.getEmail());
    }
}
