package com.mohanjp.store;

import com.mohanjp.store.data.entity.AddressEntity;
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

		var address = AddressEntity.builder()
				.street("123 Main St")
				.city("Sanfrancisco")
				.state("California")
				.zip("94105")
				.build();

		user.addAddress(address);

		System.out.println("User: " + user);
	}
}
