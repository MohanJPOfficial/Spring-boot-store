package com.mohanjp.store.homeWork_UserRegistration.domain.notification;

import org.springframework.beans.factory.annotation.Value;

public class EmailNotificationService implements NotificationService {

    @Value("${notification.host}")
    private String host;
    @Value("${notification.port}")
    private String port;

    @Override
    public void send(String message, String recipient) {
        System.out.println("Email notification service:");
        System.out.println("host: " + host);
        System.out.println("port: " + port);
        System.out.println("message: " + message);
        System.out.println("recipient: " + recipient);
    }
}
