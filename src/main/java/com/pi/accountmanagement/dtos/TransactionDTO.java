package com.pi.accountmanagement.dtos;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TransactionDTO {

    @NotNull
    private double value;

    @NotNull
    private Long accountId;
}
