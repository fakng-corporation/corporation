package com.corporation.example;

import com.corporation.service.email.Email;
import com.corporation.service.email.EmailService;

/**
 * @author Bleschunov Dmitry
 */
public class EmailServiceExample {
    public static void main(String[] args) {
        Email email = Email
                .builder()
                .to("bleshunov5b@yandex.ru")
                .from("fakng-corporation@proton.me")
                .htmlBody("<h1>I was sent from EmailServiceExample class!</h1>")
                .textBody("For the case if your email client does not support html")
                .subject("Ho-ho-ho-ho!")
                .build();

        EmailService emailService = new EmailService();
        emailService.sendEmail(email);
    }
}
