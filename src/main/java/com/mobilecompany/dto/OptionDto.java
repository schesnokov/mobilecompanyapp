package com.mobilecompany.dto;

import com.mobilecompany.entities.Option;

import java.math.BigDecimal;

public class OptionDto {

    private Integer id;
    private String name;
    private BigDecimal price;
    private BigDecimal connectionCost;

    public OptionDto() { }

    public OptionDto(Option option) {
        convertToDto(option);
    }

    public void convertToDto(Option entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.price = entity.getPrice();
        this.connectionCost = entity.getConnectionCost();
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
}