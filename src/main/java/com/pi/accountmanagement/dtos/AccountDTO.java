package com.pi.accountmanagement.dtos;


import com.pi.accountmanagement.enums.AccountType;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AccountDTO {

    @NotNull
    private double balance;

    @NotNull
    private double dailyWithdrawalLimit;

    @NotNull
    private boolean active;

    @NotNull
    private AccountType accountType;

    @NotNull
    private Long peopleId;



}
