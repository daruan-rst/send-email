package com.bank.sendemail.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "sent_email")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int emailId;

    private String email;

    private String currentAccount;

    private String targetAccount;

    private String userName;

    @Column(columnDefinition = "TEXT")
    private String text;

    private BigDecimal ammount;

    private String operation;

    private LocalDateTime sentTime;

    private String subject = operation+" "+sentTime;

    @Enumerated(EnumType.STRING)
    private DeliveredStatus status;

}
