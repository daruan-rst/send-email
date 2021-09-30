package com.bank.sendemail.service;


import com.bank.sendemail.domain.Email;
import com.bank.sendemail.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private JavaMailSender emailSender;

    public void sendEmail(Email mail) throws MailException {
        emailRepository.save(mail);
        mail.setSentTime(LocalDateTime.now());
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("bank.application979@gmail.com");
        message.setTo(mail.getEmail());
        message.setSubject(mail.getSubject());
        message.setText(mail.getText());
        emailSender.send(message);
    }
    
}
