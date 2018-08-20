package com.mobilecompany.controllers.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * The type New contract helper.
 */
public class NewContractHelper {

    private String number;
    private BigDecimal balance;
    private Integer tariffId;
    private List<Integer> optionsIds;

    /**
     * Gets number.
     *
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * Sets number.
     *
     * @param number the number
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Gets balance.
     *
     * @return the balance
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * Sets balance.
     *
     * @param balance the balance
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * Gets tariff id.
     *
     * @return the tariff id
     */
    public Integer getTariffId() {
        return tariffId;
    }

    /**
     * Sets tariff id.
     *
     * @param tariffId the tariff id
     */
    public void setTariffId(Integer tariffId) {
        this.tariffId = tariffId;
    }

    /**
     * Gets options ids.
     *
     * @return the options ids
     */
    public List<Integer> getOptionsIds() {
        return optionsIds;
    }

    /**
     * Sets options ids.
     *
     * @param optionsIds the options ids
     */
    public void setOptionsIds(List<Integer> optionsIds) {
        this.optionsIds = optionsIds;
    }
}
