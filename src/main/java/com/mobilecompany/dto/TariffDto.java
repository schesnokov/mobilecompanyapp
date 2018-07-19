package com.mobilecompany.dto;

import com.mobilecompany.entities.Contract;

import java.math.BigDecimal;
import java.util.Set;

public class TariffDto {

    private int id;
    private String name;
    private BigDecimal price;
    private String description;
    private Set<OptionDto> options;
    private Contract contract;


    public TariffDto() {
    }

    public TariffDto(int id, String name, BigDecimal price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
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
