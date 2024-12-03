package com.ensaj.examsEnsaj.examsEnsaj.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class mailService {
    JavaMailSender mailSender;
    public  void sendEmail(String to, String subject, String body) {
       SimpleMailMessage  message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom("abdellahelgharbi200229@gmail.com");
        mailSender.send(message);
    }
}
