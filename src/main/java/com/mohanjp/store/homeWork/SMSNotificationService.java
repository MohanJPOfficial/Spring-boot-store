package com.mohanjp.store.homeWork;

import org.springframework.stereotype.Service;

@Service("SMS")
public class SMSNotificationService implements NotificationService {

    @Override
    public void send(String message) {
        System.out.println("SMS Notification: " + message);
    }
}
