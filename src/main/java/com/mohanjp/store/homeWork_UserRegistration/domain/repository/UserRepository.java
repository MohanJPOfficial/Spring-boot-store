package com.mohanjp.store.homeWork_UserRegistration.domain.repository;

import com.mohanjp.store.homeWork_UserRegistration.domain.model.User;

public interface UserRepository {

    void save(User user);

    User findByEmail(String email);
}
