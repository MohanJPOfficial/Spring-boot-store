package com.mohanjp.store.homeWork_UserRegistration.data.repository;

import com.mohanjp.store.homeWork_UserRegistration.domain.model.User;
import com.mohanjp.store.homeWork_UserRegistration.domain.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserRepository implements UserRepository {

    private final Map<String, User> userMap = new HashMap<>();

    @Override
    public void save(User user) {
        System.out.println("Saving user to in-memory repository: " + user);
        userMap.put(user.getEmail(), user);
    }

    @Override
    public User findByEmail(String email) {
        return userMap.getOrDefault(email, null);
    }
}
