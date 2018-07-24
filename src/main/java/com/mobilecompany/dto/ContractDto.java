package com.mobilecompany.dto;

import java.math.BigDecimal;

public class ContractDto {
    private int id;
    private String number;
    private BigDecimal balance;
    private int isBlocked;
    private UserDto userDto;
    private TariffDto tariffDto;

    public ContractDto() {
    }

    public ContractDto(int id, String number, BigDecimal balance, byte isBlocked) {
        this.id = id;
        this.number = number;
        this.balance = balance;
        this.isBlocked = isBlocked;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }

    public BigDecimal getBalance() {
        return balance;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public int getIsBlocked() {
        return isBlocked;
    }
    public void setIsBlocked(int isBlocked) {
        this.isBlocked = isBlocked;
    }

    public UserDto getUser() {
        return userDto;
    }
    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public TariffDto getTariff() {
        return tariffDto;
    }
    public void setTariff(TariffDto tariffDto) {
        this.tariffDto = tariffDto;
    }
}
