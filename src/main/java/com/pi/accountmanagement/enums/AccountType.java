package com.pi.accountmanagement.enums;

public enum AccountType {

    CORRENTE(1, "CORRENTE"),
    POUPANCA(2, "POUPANCA");

    private int code;
    private String desc;

    private AccountType(int code, String desc) {
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