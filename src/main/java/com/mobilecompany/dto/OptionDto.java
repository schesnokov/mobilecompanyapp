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
    private int isBlocked;
    private Set<OptionDto> dependentFirst;
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

    public int getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(int isBlocked) {
        this.isBlocked = isBlocked;
    }

    public Set<OptionDto> getDependentFirst() {
        return dependentFirst;
    }

    public void setDependentFirst(Set<OptionDto> dependentFirst) {
        this.dependentFirst = dependentFirst;
    }


    public Set<OptionDto> getConflictedFirst() {
        return conflictedFirst;
    }

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