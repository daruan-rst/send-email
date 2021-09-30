package com.bank.sendemail.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class EmailDto {


    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String currentAccount;
    @NotBlank
    private String targetAccount;
    @NotBlank
    private BigDecimal ammount;
    @NotBlank
    private String operation;


}
