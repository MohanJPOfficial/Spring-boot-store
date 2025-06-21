package com.mohanjp.store.services;

import com.mohanjp.store.entity.CategoryEntity;
import com.mohanjp.store.entity.ProductEntity;
import com.mohanjp.store.entity.UserEntity;
import com.mohanjp.store.repositories.AddressRepository;
import com.mohanjp.store.repositories.ProductRepository;
import com.mohanjp.store.repositories.ProfileRepository;
import com.mohanjp.store.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final EntityManager entityManager;
    private final ProfileRepository profileRepository;
    private final AddressRepository addressRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryEntityRepository;

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

    public void fetchAddress() {
        var address = addressRepository.findById(1L).orElseThrow();
        System.out.println(address);
    }

    @Transactional
    public void saveProduct() {

        //var category = new CategoryEntity("Electronices");

        var category = categoryEntityRepository.findById((byte) 1).orElseThrow();

        var product = ProductEntity.builder()
                .name("Mobile")
                .description("Handheld device")
                .price(BigDecimal.valueOf(1200.00))
                .category(category)
                .build();

        productRepository.save(product);
    }

    @Transactional
    public void wishlistProduct() {
        var user = userRepository.findById(1L).orElseThrow();
        var products = productRepository.findAll();

        products.forEach(user::addFavoriteProduct);
        userRepository.save(user);
    }

    public void deleteProduct() {
        productRepository.deleteById(2L);
    }

    public void findProduct() {
        productRepository.findProducts(BigDecimal.valueOf(1000), BigDecimal.valueOf(1500)).forEach(System.out::println);
    }

    @Transactional
    public void updatePriceByCategory() {
        productRepository.updatePriceByCategory(BigDecimal.valueOf(1000), (byte) 1);
    }

    public void fetchProducts() {
        var products = productRepository.findByCategory(new CategoryEntity((byte)1));
        products.forEach(System.out::println);
    }

    @Transactional
    public void fetchUser() {
        var user = userRepository.findByEmail("jpmohan@gmail.com");
        System.out.println(user);
    }

    @Transactional
    public void fetchUsersWithAddresses() {
        userRepository.findUsersWithAddresses().forEach( u -> {
            System.out.println(u);
            u.getAddresses().forEach(System.out::println);
        });

    }
}
