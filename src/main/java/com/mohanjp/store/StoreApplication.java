package com.mohanjp.store;

import com.mohanjp.store.homeWork.NotificationManager;
import com.mohanjp.store.homeWork_UserRegistration.domain.model.User;
import com.mohanjp.store.homeWork_UserRegistration.presentation.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(StoreApplication.class, args);

		//var orderService = context.getBean(OrderService.class);
		//orderService.placeOrder(17.53);

		var userService = context.getBean(UserService.class);
		var user = new User(23L, "jpmohan007@gmail.com", "22the333", "Mohan");
		var user1 = new User(23L, "jpmohan007@gmail.com", "22the333", "Mohan");

		userService.registerUser(user);
		userService.registerUser(user1);
	}
}
