package com.mobilecompany.controllers.model;

import java.math.BigDecimal;
import java.util.List;

public class NewOptionHelper {

    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal connectionCost;
    private List<Integer> compatibleTariffsIds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<Integer> getCompatibleTariffsIds() {
        return compatibleTariffsIds;
    }

    public void setCompatibleTariffsIds(List<Integer> compatibleTariffsIds) {
        this.compatibleTariffsIds = compatibleTariffsIds;
    }
}
