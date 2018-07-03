package com.mobilecompany.dto;

import java.math.BigDecimal;

public class OptionDto {

    private Integer id;
    private String name;
    private BigDecimal price;
    private BigDecimal connectionCost;

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
}