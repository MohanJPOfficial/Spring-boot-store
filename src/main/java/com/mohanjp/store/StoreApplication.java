package com.mohanjp.store;

import com.mohanjp.store.data.entity.AddressEntity;
import com.mohanjp.store.data.entity.ProfileEntity;
import com.mohanjp.store.data.entity.UserEntity;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {

		//ApplicationContext context = SpringApplication.run(StoreApplication.class, args);

		var user = UserEntity.builder()
				.name("Mohan")
				.password("22the333")
				.email("jpmohan@gmail.com")
				.build();

		var profile = ProfileEntity.builder()
						.bio("Software Engineer with a passion for coding and technology.")
						.build();

		user.setProfile(profile);
		profile.setUser(user);

		System.out.println("User: " + user);
	}
}
