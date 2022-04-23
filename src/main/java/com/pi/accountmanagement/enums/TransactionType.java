package com.pi.accountmanagement.enums;

public enum TransactionType {

    DEPOSIT(1, "DEPOSITO"),
    WITHDRAW(2, "SAQUE");

    private int code;
    private String desc;

    private TransactionType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}