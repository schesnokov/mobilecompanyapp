package com.mobilecompany.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

/**
 * The type Option dto.
 */
public class OptionDto implements Serializable {

    private Integer id;
    private String name;
    private BigDecimal price;
    private BigDecimal connectionCost;
    private String description;
    private Set<OptionDto> dependentFirst;
    private Set<OptionDto> conflictedFirst;


    /**
     * Instantiates a new Option dto.
     */
    public OptionDto() {
    }

    /**
     * Instantiates a new Option dto.
     *
     * @param id             the id
     * @param name           the name
     * @param price          the price
     * @param connectionCost the connection cost
     */
    public OptionDto(Integer id, String name, BigDecimal price, BigDecimal connectionCost) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.connectionCost = connectionCost;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }

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
     * Gets dependent first.
     *
     * @return the dependent first
     */
    public Set<OptionDto> getDependentFirst() {
        return dependentFirst;
    }

    /**
     * Sets dependent first.
     *
     * @param dependentFirst the dependent first
     */
    public void setDependentFirst(Set<OptionDto> dependentFirst) {
        this.dependentFirst = dependentFirst;
    }


    /**
     * Gets conflicted first.
     *
     * @return the conflicted first
     */
    public Set<OptionDto> getConflictedFirst() {
        return conflictedFirst;
    }

    /**
     * Sets conflicted first.
     *
     * @param conflictedFirst the conflicted first
     */
    public void setConflictedFirst(Set<OptionDto> conflictedFirst) {
        this.conflictedFirst = conflictedFirst;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OptionDto optionDto = (OptionDto) o;

        if (id != null ? !id.equals(optionDto.id) : optionDto.id != null) return false;
        if (name != null ? !name.equals(optionDto.name) : optionDto.name != null) return false;
        if (price != null ? !price.equals(optionDto.price) : optionDto.price != null) return false;
        if (connectionCost != null ? !connectionCost.equals(optionDto.connectionCost) : optionDto.connectionCost != null)
            return false;
        if (description != null ? !description.equals(optionDto.description) : optionDto.description != null)
            return false;
        if (dependentFirst != null ? !dependentFirst.equals(optionDto.dependentFirst) : optionDto.dependentFirst != null)
            return false;
        return conflictedFirst != null ? conflictedFirst.equals(optionDto.conflictedFirst) : optionDto.conflictedFirst == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (connectionCost != null ? connectionCost.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (dependentFirst != null ? dependentFirst.hashCode() : 0);
        result = 31 * result + (conflictedFirst != null ? conflictedFirst.hashCode() : 0);
        return result;
    }
}