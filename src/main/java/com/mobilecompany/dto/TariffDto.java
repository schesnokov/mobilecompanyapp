package com.mobilecompany.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

public class TariffDto implements Serializable {

    private int id;
    private String tariffName;
    private BigDecimal tariffPrice;
    private String tariffDescription;
    private int isBlocked;
    private Set<OptionDto> availableOptions;

    public TariffDto() {
    }

    public TariffDto(int id, String tariffName, BigDecimal tariffPrice, String tariffDescription) {
        this.id = id;
        this.tariffName = tariffName;
        this.tariffPrice = tariffPrice;
        this.tariffDescription = tariffDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTariffName() {
        return tariffName;
    }

    public void setTariffName(String tariffName) {
        this.tariffName = tariffName;
    }


    public BigDecimal getTariffPrice() {
        return tariffPrice;
    }

    public void setTariffPrice(BigDecimal tariffPrice) {
        this.tariffPrice = tariffPrice;
    }


    public String getTariffDescription() {
        return tariffDescription;
    }

    public void setTariffDescription(String tariffDescription) {
        this.tariffDescription = tariffDescription;
    }

    public int getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(int isBlocked) {
        this.isBlocked = isBlocked;
    }

    public Set<OptionDto> getAvailableOptions() {
        return availableOptions;
    }

    public void setAvailableOptions(Set<OptionDto> availableOptions) {
        this.availableOptions = availableOptions;
    }
}
