package com.mohanjp.store.services;

import com.mohanjp.store.data.entity.UserEntity;
import com.mohanjp.store.repositories.ProfileRepository;
import com.mohanjp.store.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final EntityManager entityManager;
    private final ProfileRepository profileRepository;

    @Transactional
    public void showEntityStates() {
        var user = UserEntity.builder()
                .name("Mohan")
                .email("jpmohan@gmail.com")
                .password("password123")
                .build();

        if (entityManager.contains(user)) {
            System.out.println("User is persistence by the EntityManager.");
        } else {
            System.out.println("User is transient / detached by the EntityManager.");
        }

        userRepository.save(user);

        if (entityManager.contains(user))
            System.out.println("Persistent");
        else
            System.out.println("Transient / Detached");
    }

    @Transactional
    public void showRelatedEntities() {
        var profile = profileRepository.findById(1L).orElseThrow();
        System.out.println(profile.getUser().getEmail());
    }
}
