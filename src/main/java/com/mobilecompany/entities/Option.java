package com.mobilecompany.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * The type Option.
 */
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

    /**
     * Instantiates a new Option.
     */
    public Option() {
        dependentFirst = new HashSet<>();
        conflictedFirst = new HashSet<>();
        availableTariffOption = new HashSet<>();
        contracts = new HashSet<>();
    }

    /**
     * Instantiates a new Option.
     *
     * @param id             the id
     * @param name           the name
     * @param price          the price
     * @param connectionCost the connection cost
     * @param description    the description
     */
    public Option(int id, String name, BigDecimal price, BigDecimal connectionCost, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.connectionCost = connectionCost;
        this.description = description;
    }

    /**
     * Add dependent first.
     *
     * @param option the option
     */
    public void addDependentFirst(Option option) {
        this.getDependentFirst().add(option);
    }

    /**
     * Add conflicted.
     *
     * @param option the option
     */
    public void addConflicted(Option option) {
        this.getConflictedFirst().add(option);
    }

    /**
     * Add dependent first.
     *
     * @param options the options
     */
    public void addDependentFirst(Set<Option> options) {
        this.getDependentFirst().addAll(options);
    }

    /**
     * Add conflicted.
     *
     * @param options the options
     */
    public void addConflicted(Set<Option> options) {
        this.getConflictedFirst().addAll(options);
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
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
     * Gets option is blocked.
     *
     * @return the option is blocked
     */
    public int getOptionIsBlocked() {
        return optionIsBlocked;
    }

    /**
     * Sets option is blocked.
     *
     * @param isBlocked the is blocked
     */
    public void setOptionIsBlocked(int isBlocked) {
        this.optionIsBlocked = isBlocked;
    }

    /**
     * Gets dependent first.
     *
     * @return the dependent first
     */
    public Set<Option> getDependentFirst() {
        return dependentFirst;
    }

    /**
     * Sets dependent first.
     *
     * @param dependentFirst the dependent first
     */
    public void setDependentFirst(Set<Option> dependentFirst) {
        this.dependentFirst = dependentFirst;
    }


    /**
     * Gets conflicted first.
     *
     * @return the conflicted first
     */
    public Set<Option> getConflictedFirst() {
        return conflictedFirst;
    }

    /**
     * Sets conflicted first.
     *
     * @param conflictedFirst the conflicted first
     */
    public void setConflictedFirst(Set<Option> conflictedFirst) {
        this.conflictedFirst = conflictedFirst;
    }


    /**
     * Gets available tariff option.
     *
     * @return the available tariff option
     */
    public Set<Tariff> getAvailableTariffOption() {
        return availableTariffOption;
    }

    /**
     * Sets available tariff option.
     *
     * @param availableTariffOption the available tariff option
     */
    public void setAvailableTariffOption(Set<Tariff> availableTariffOption) {
        this.availableTariffOption = availableTariffOption;
    }

    /**
     * Gets contracts.
     *
     * @return the contracts
     */
    public Set<Contract> getContracts() {
        return contracts;
    }

    /**
     * Sets contracts.
     *
     * @param contracts the contracts
     */
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
