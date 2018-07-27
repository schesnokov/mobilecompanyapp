package com.mobilecompany.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

public class OptionDto implements Serializable {

    private Integer id;
    private String name;
    private BigDecimal price;
    private BigDecimal connectionCost;
    private String description;
    private Set<TariffDto> tariffs;
    private Set<TariffDto> dependentFirst;
    private Set<TariffDto> dependentSecond;
    private Set<OptionDto> conflictedFirst;


    public OptionDto() {
    }

    public OptionDto(Integer id, String name, BigDecimal price, BigDecimal connectionCost) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.connectionCost = connectionCost;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public BigDecimal getConnectionCost() {
        return connectionCost;
    }

    public void setConnectionCost(BigDecimal connectionCost) {
        this.connectionCost = connectionCost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<TariffDto> getTariffs() {
        return tariffs;
    }

    public void setTariffs(Set<TariffDto> tariffs) {
        this.tariffs = tariffs;
    }
}