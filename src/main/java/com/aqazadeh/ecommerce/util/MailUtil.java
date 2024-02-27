package com.aqazadeh.ecommerce.util;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 21.02.2024
 * Time: 15:55
 */

@Component
@RequiredArgsConstructor
public class MailUtil {
    private final JavaMailSender emailSender;

    public void sendMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@aqazadeh.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText("http://localhost:8080/api/v1/auth/activate?token=" + text);
        emailSender.send(message);
    }
}
