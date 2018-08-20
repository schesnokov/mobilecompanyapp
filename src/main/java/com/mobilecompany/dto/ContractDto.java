package com.mobilecompany.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * The type Contract dto.
 */
public class ContractDto implements Serializable {
    private int id;
    private String number;
    private BigDecimal balance;
    private int isBlocked;
    private UserDto userDto;
    private TariffDto tariffDto;
    private Set<OptionDto> selectedOptions = new HashSet<>();

    /**
     * Instantiates a new Contract dto.
     */
    public ContractDto() {
    }

    /**
     * Instantiates a new Contract dto.
     *
     * @param id        the id
     * @param number    the number
     * @param balance   the balance
     * @param isBlocked the is blocked
     */
    public ContractDto(int id, String number, BigDecimal balance, byte isBlocked) {
        this.id = id;
        this.number = number;
        this.balance = balance;
        this.isBlocked = isBlocked;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

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
     * Gets is blocked.
     *
     * @return the is blocked
     */
    public int getIsBlocked() {
        return isBlocked;
    }

    /**
     * Sets is blocked.
     *
     * @param isBlocked the is blocked
     */
    public void setIsBlocked(int isBlocked) {
        this.isBlocked = isBlocked;
    }

    /**
     * Gets user dto.
     *
     * @return the user dto
     */
    public UserDto getUserDto() {
        return userDto;
    }

    /**
     * Sets user dto.
     *
     * @param userDto the user dto
     */
    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    /**
     * Gets tariff.
     *
     * @return the tariff
     */
    public TariffDto getTariff() {
        return tariffDto;
    }

    /**
     * Sets tariff.
     *
     * @param tariffDto the tariff dto
     */
    public void setTariff(TariffDto tariffDto) {
        this.tariffDto = tariffDto;
    }

    /**
     * Gets tariff dto.
     *
     * @return the tariff dto
     */
    public TariffDto getTariffDto() {
        return tariffDto;
    }

    /**
     * Sets tariff dto.
     *
     * @param tariffDto the tariff dto
     */
    public void setTariffDto(TariffDto tariffDto) {
        this.tariffDto = tariffDto;
    }

    /**
     * Gets selected options.
     *
     * @return the selected options
     */
    public Set<OptionDto> getSelectedOptions() {
        return selectedOptions;
    }

    /**
     * Sets selected options.
     *
     * @param selectedOptions the selected options
     */
    public void setSelectedOptions(Set<OptionDto> selectedOptions) {
        this.selectedOptions = selectedOptions;
    }
}
