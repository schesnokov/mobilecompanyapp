package com.mobilecompany.controllers.model;

import java.math.BigDecimal;
import java.util.List;

public class NewContractHelper {

    private String number;
    private BigDecimal balance;
    private Integer tariffId;
    private List<Integer> optionsIds;

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

    public Integer getTariffId() {
        return tariffId;
    }

    public void setTariffId(Integer tariffId) {
        this.tariffId = tariffId;
    }

    public List<Integer> getOptionsIds() {
        return optionsIds;
    }

    public void setOptionsIds(List<Integer> optionsIds) {
        this.optionsIds = optionsIds;
    }
}
