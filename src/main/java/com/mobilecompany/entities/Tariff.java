package com.mobilecompany.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

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

    @Column(name = "isBlocked")
    private int isBlocked;

    @JoinTable(name = "availableoptions", joinColumns = {
            @JoinColumn(name = "tariffId", referencedColumnName = "id", nullable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "optionId", referencedColumnName = "id", nullable = false)
            })
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Option> availableOptions;

    public Tariff() {
    }

    public Tariff(String tariffDescription, int id, String tariffName, BigDecimal tariffPrice) {
        this.id = id;
        this.tariffName = tariffName;
        this.tariffPrice = tariffPrice;
        this.tariffDescription = tariffDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTariffName() {
        return tariffName;
    }

    public void setTariffName(String name) {
        this.tariffName = name;
    }

    public BigDecimal getTariffPrice() {
        return tariffPrice;
    }

    public void setTariffPrice(BigDecimal price) {
        this.tariffPrice = price;
    }

    public String getTariffDescription() {
        return tariffDescription;
    }

    public void setTariffDescription(String description) {
        this.tariffDescription = description;
    }

    public Set<Option> getAvailableOptions() {
        return availableOptions;
    }

    public int getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(int isBlocked) {
        this.isBlocked = isBlocked;
    }

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
