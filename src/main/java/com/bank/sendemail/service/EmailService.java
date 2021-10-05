package com.bank.sendemail.service;


import com.bank.sendemail.domain.DeliveredStatus;
import com.bank.sendemail.domain.Email;
import com.bank.sendemail.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private JavaMailSender emailSender;

    public Email sendEmail(Email mail) {
        mail.setSentTime(LocalDateTime.now());
        mail.setText(emailText(mail));
        mail.setSubject(mail.getOperation() + "-" +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy @ HH:mm")));
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("bank.application979@gmail.com");
            message.setTo(mail.getEmail());
            message.setSubject(mail.getSubject());
            message.setText(mail.getText());
            emailSender.send(message);
            mail.setStatus(DeliveredStatus.SENT);
        }catch (MailException e){
            mail.setStatus(DeliveredStatus.ERROR);
        }finally {
            return emailRepository.save(mail);
        }

    }

    private String emailText(Email email){
        return saudacao() +"!\nFoi realizad"+operationType(email) + " no montante de R$"+
                email.getAmmount()+". Se a operação parecer suspeita ou se não identificar o procedimento, entre em contato\n" +
                "\n" +
                "\n" +
                "Atenciosamente,\n" +
                "Seu Banco"; }

    private String saudacao(){
        int hour = LocalDateTime.now().getHour();
        String saudacao;
        if (hour>6 && hour<12){
            saudacao = "Bom dia";
        }else if(hour>=12 && hour <18){
            saudacao = "Boa tarde";
        }else{
            saudacao = "Boa noite";
        }return saudacao;
    }

    private String operationType(Email email){
        String operation ="";
        switch (email.getOperation()){
            case "DEPOSIT":
                operation = "o um depósito na sua conta " + email.getCurrentAccount();
                break;
            case "WITHDRAW":
                operation = "o um saque da sua conta " + email.getCurrentAccount();
                break;
            case "TRANSFER_PIX":
                operation = "a uma transferência tipo PIX da sua conta " + email.getCurrentAccount()
                        + " para a conta " + email.getTargetAccount();
                break;
            case "TRANSFER_TED":
                operation = "a uma transferência tipo TED da sua conta " + email.getCurrentAccount()
                        + " para a conta " + email.getTargetAccount();
                break;
            case "TRANSFER_DOC":
                operation = "a uma transferência tipo DOC da sua conta " + email.getCurrentAccount()
                        + " para a conta " + email.getTargetAccount();
                break;
            case "PREPAID_CELL_CREDIT":
            operation = "a uma recarga de celular da sua conta " + email.getCurrentAccount();
            break;
        }

        return operation;}

}
