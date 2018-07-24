package com.mobilecompany.dto;

import com.mobilecompany.entities.Contract;

import java.math.BigDecimal;
import java.util.Set;

public class TariffDto {

    private int id;
    private String tariffName;
    private BigDecimal tariffPrice;
    private String tariffDescription;
    private Set<OptionDto> options;
    private Contract contract;


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


    public Set<OptionDto> getOptions() {
        return options;
    }
    public void setOptions(Set<OptionDto> options) {
        this.options = options;
    }


    public Contract getContract() {
        return contract;
    }
    public void setContract(Contract contract) {
        this.contract = contract;
    }
}
