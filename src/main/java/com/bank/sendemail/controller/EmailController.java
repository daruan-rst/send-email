package com.bank.sendemail.controller;


import com.bank.sendemail.domain.Email;
import com.bank.sendemail.dto.EmailDto;
import com.bank.sendemail.service.EmailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send-email")
    public ResponseEntity<Email> sendEmail(@RequestBody @Valid EmailDto emailDto){
        Email mail = new Email();
        BeanUtils.copyProperties(emailDto, mail);
        emailService.sendEmail(mail);
        return new ResponseEntity<>(mail, HttpStatus.CREATED);
    }
}
