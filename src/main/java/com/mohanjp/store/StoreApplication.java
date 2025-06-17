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
	}
}
