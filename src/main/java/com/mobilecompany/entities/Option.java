package com.mobilecompany.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Option")
@Table(name = "options")
public class Option {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "optionName")
    private String name;

    @Column(name = "optionPrice")
    private BigDecimal price;

    @Column(name = "connectionPrice")
    private BigDecimal connectionCost;

    @Column(name = "optionDescription")
    private String description;

    @Column(name = "optionIsBlocked")
    private int optionIsBlocked;

    @JoinTable(name = "dependentoptions", joinColumns = {
            @JoinColumn(name = "firstOption", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "secondOption", referencedColumnName = "id")
    })
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private Set<Option> dependentFirst = new HashSet<>();

    @JoinTable(name = "conflictedoptions", joinColumns = {
            @JoinColumn(name = "firstOption", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "secondOption", referencedColumnName = "id")
    })
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private Set<Option> conflictedFirst = new HashSet<>();

    @ManyToMany(mappedBy = "availableOptions", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private Set<Tariff> availableTariffOption = new HashSet<>();

    @JoinTable(name = "selectedoptions", joinColumns = {
            @JoinColumn(name = "optionId", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "contractId", referencedColumnName = "id", nullable = false)
    })
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private Set<Contract> contracts = new HashSet<>();

    public Option() {
        dependentFirst = new HashSet<>();
        conflictedFirst = new HashSet<>();
        availableTariffOption = new HashSet<>();
        contracts = new HashSet<>();
    }

    public Option(int id, String name, BigDecimal price, BigDecimal connectionCost, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.connectionCost = connectionCost;
        this.description = description;
    }

    public void addDependentFirst(Option option) {
        this.getDependentFirst().add(option);
    }

    public void addConflicted(Option option) {
        this.getConflictedFirst().add(option);
    }

    public void addDependentFirst(Set<Option> options) {
        this.getDependentFirst().addAll(options);
    }

    public void addConflicted(Set<Option> options) {
        this.getConflictedFirst().addAll(options);
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

    public int getOptionIsBlocked() {
        return optionIsBlocked;
    }

    public void setOptionIsBlocked(int isBlocked) {
        this.optionIsBlocked = isBlocked;
    }

    public Set<Option> getDependentFirst() {
        return dependentFirst;
    }

    public void setDependentFirst(Set<Option> dependentFirst) {
        this.dependentFirst = dependentFirst;
    }


    public Set<Option> getConflictedFirst() {
        return conflictedFirst;
    }

    public void setConflictedFirst(Set<Option> conflictedFirst) {
        this.conflictedFirst = conflictedFirst;
    }


    public Set<Tariff> getAvailableTariffOption() {
        return availableTariffOption;
    }

    public void setAvailableTariffOption(Set<Tariff> availableTariffOption) {
        this.availableTariffOption = availableTariffOption;
    }

    public Set<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(Set<Contract> contracts) {
        this.contracts = contracts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Option option = (Option) o;

        if (id != option.id) return false;
        if (name != null ? !name.equals(option.name) : option.name != null) return false;
        if (price != null ? !price.equals(option.price) : option.price != null) return false;
        if (connectionCost != null ? !connectionCost.equals(option.connectionCost) : option.connectionCost != null)
            return false;
        if (description != null ? !description.equals(option.description) : option.description != null) return false;
        return contracts != null ? contracts.equals(option.contracts) : option.contracts == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (connectionCost != null ? connectionCost.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Option{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", connectionCost=" + connectionCost +
                ", description=" + description +
                '}';
    }
}
