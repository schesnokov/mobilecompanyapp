package com.mobilecompany.controllers.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * The type New option helper.
 */
public class NewOptionHelper {

    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal connectionCost;
    private List<Integer> compatibleTariffsIds;
    private List<Integer> dependentIds;
    private List<Integer> conflictedIds;

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Gets connection cost.
     *
     * @return the connection cost
     */
    public BigDecimal getConnectionCost() {
        return connectionCost;
    }

    /**
     * Sets connection cost.
     *
     * @param connectionCost the connection cost
     */
    public void setConnectionCost(BigDecimal connectionCost) {
        this.connectionCost = connectionCost;
    }

    /**
     * Gets compatible tariffs ids.
     *
     * @return the compatible tariffs ids
     */
    public List<Integer> getCompatibleTariffsIds() {
        return compatibleTariffsIds;
    }

    /**
     * Sets compatible tariffs ids.
     *
     * @param compatibleTariffsIds the compatible tariffs ids
     */
    public void setCompatibleTariffsIds(List<Integer> compatibleTariffsIds) {
        this.compatibleTariffsIds = compatibleTariffsIds;
    }

    /**
     * Gets dependent ids.
     *
     * @return the dependent ids
     */
    public List<Integer> getDependentIds() {
        return dependentIds;
    }

    /**
     * Sets dependent ids.
     *
     * @param dependentIds the dependent ids
     */
    public void setDependentIds(List<Integer> dependentIds) {
        this.dependentIds = dependentIds;
    }

    /**
     * Gets conflicted ids.
     *
     * @return the conflicted ids
     */
    public List<Integer> getConflictedIds() {
        return conflictedIds;
    }

    /**
     * Sets conflicted ids.
     *
     * @param conflictedIds the conflicted ids
     */
    public void setConflictedIds(List<Integer> conflictedIds) {
        this.conflictedIds = conflictedIds;
    }
}
