package com.mobilecompany.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

/**
 * The type Tariff.
 */
@Entity(name = "Tariff")
@Table(name = "tariffs")
public class Tariff {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "tariffName")
    private String tariffName;

    @Column(name = "tariffPrice")
    private BigDecimal tariffPrice;

    @Column(name = "tariffDescription")
    private String tariffDescription;

    @Column(name = "tariffIsBlocked")
    private int tariffIsBlocked;

    @JoinTable(name = "availableoptions", joinColumns = {
            @JoinColumn(name = "tariffId", referencedColumnName = "id", nullable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "optionId", referencedColumnName = "id", nullable = false)
            })
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Option> availableOptions;

    /**
     * Instantiates a new Tariff.
     */
    public Tariff() {
    }

    /**
     * Instantiates a new Tariff.
     *
     * @param tariffDescription the tariff description
     * @param id                the id
     * @param tariffName        the tariff name
     * @param tariffPrice       the tariff price
     */
    public Tariff(String tariffDescription, int id, String tariffName, BigDecimal tariffPrice) {
        this.id = id;
        this.tariffName = tariffName;
        this.tariffPrice = tariffPrice;
        this.tariffDescription = tariffDescription;
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
     * Gets tariff name.
     *
     * @return the tariff name
     */
    public String getTariffName() {
        return tariffName;
    }

    /**
     * Sets tariff name.
     *
     * @param name the name
     */
    public void setTariffName(String name) {
        this.tariffName = name;
    }

    /**
     * Gets tariff price.
     *
     * @return the tariff price
     */
    public BigDecimal getTariffPrice() {
        return tariffPrice;
    }

    /**
     * Sets tariff price.
     *
     * @param price the price
     */
    public void setTariffPrice(BigDecimal price) {
        this.tariffPrice = price;
    }

    /**
     * Gets tariff description.
     *
     * @return the tariff description
     */
    public String getTariffDescription() {
        return tariffDescription;
    }

    /**
     * Sets tariff description.
     *
     * @param description the description
     */
    public void setTariffDescription(String description) {
        this.tariffDescription = description;
    }

    /**
     * Gets available options.
     *
     * @return the available options
     */
    public Set<Option> getAvailableOptions() {
        return availableOptions;
    }

    /**
     * Gets tariff is blocked.
     *
     * @return the tariff is blocked
     */
    public int getTariffIsBlocked() {
        return tariffIsBlocked;
    }

    /**
     * Sets tariff is blocked.
     *
     * @param isBlocked the is blocked
     */
    public void setTariffIsBlocked(int isBlocked) {
        this.tariffIsBlocked = isBlocked;
    }

    /**
     * Sets available options.
     *
     * @param availableOptions the available options
     */
    public void setAvailableOptions(Set<Option> availableOptions) {
        this.availableOptions = availableOptions;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tariff tariff = (Tariff) o;

        if (id != tariff.id) return false;
        if (tariffName != null ? !tariffName.equals(tariff.tariffName) : tariff.tariffName != null) return false;
        if (tariffPrice != null ? !tariffPrice.equals(tariff.tariffPrice) : tariff.tariffPrice != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (tariffName != null ? tariffName.hashCode() : 0);
        result = 31 * result + (tariffPrice != null ? tariffPrice.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Tariff{" +
                "id=" + id +
                ", name='" + tariffName + '\'' +
                ", price=" + tariffPrice +
                ", description=" + tariffDescription +
                '}';
    }
}
