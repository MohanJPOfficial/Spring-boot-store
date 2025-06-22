package com.mohanjp.store;

import com.mohanjp.store.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;

@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(StoreApplication.class, args);

		var service = context.getBean(UserService.class);
		service.fetchProductsBySpecification("Laptop", BigDecimal.valueOf(800), BigDecimal.valueOf(1500));
	}
}
