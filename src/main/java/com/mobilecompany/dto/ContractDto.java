package com.mobilecompany.dto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class ContractDto {
    private int id;
    private String number;
    private BigDecimal balance;
    private int isBlocked;
    private UserDto userDto;
    private TariffDto tariffDto;
    private Set<OptionDto> selectedOptions = new HashSet<>();

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

    public UserDto getUserDto() {
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

    public TariffDto getTariffDto() {
        return tariffDto;
    }
    public void setTariffDto(TariffDto tariffDto) {
        this.tariffDto = tariffDto;
    }

    public Set<OptionDto> getSelectedOptions() {
        return selectedOptions;
    }
    public void setSelectedOptions(Set<OptionDto> selectedOptions) {
        this.selectedOptions = selectedOptions;
    }
}
