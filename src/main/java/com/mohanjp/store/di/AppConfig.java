package com.mohanjp.store.di;

import com.mohanjp.store.OrderService;
import com.mohanjp.store.homeWork_UserRegistration.data.repository.InMemoryUserRepository;
import com.mohanjp.store.homeWork_UserRegistration.domain.notification.EmailNotificationService;
import com.mohanjp.store.homeWork_UserRegistration.domain.notification.NotificationService;
import com.mohanjp.store.homeWork_UserRegistration.domain.repository.UserRepository;
import com.mohanjp.store.homeWork_UserRegistration.presentation.UserService;
import com.mohanjp.store.paymentService.PaymentService;
import com.mohanjp.store.paymentService.PaypalPaymentService;
import com.mohanjp.store.paymentService.StripePaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
public class AppConfig {

    @Value("${payment-gateway:stripe}")
    private String paymentGateway;

    @Bean
    @Primary
    public PaymentService stripe() {
        return new StripePaymentService();
    }

    @Bean
    public PaymentService paypal() {
        return new PaypalPaymentService();
    }

    @Bean
    public OrderService orderService() {
        if(paymentGateway.equals("stripe")) {
            return new OrderService(stripe());
        }

        return new OrderService(paypal());
    }

    @Bean
    public UserRepository inMemoryUserRepository() {
        return new InMemoryUserRepository();
    }

    @Bean
    public NotificationService emailNotificationService() {
        return new EmailNotificationService();
    }

    @Bean
    @Lazy
    @Scope("singleton")
    public UserService userService() {
        return new UserService(emailNotificationService(), inMemoryUserRepository());
    }
}
