package com.mobilecompany.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Tariff")
@Table(name = "tariffs")
public class Tariff {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String tariffName;

    @Column(name = "price")
    private BigDecimal tariffPrice;

    @Column(name = "description")
    private String tariffDescription;

    public Tariff() {}

    public Tariff(int id, String tariffName, BigDecimal tariffPrice, Set<Option> options) {
        this.id = id;
        this.tariffName = tariffName;
        this.tariffPrice = tariffPrice;
        this.options = options;
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

    public String getTariffDescription() { return tariffDescription; }
    public void setTariffDescription(String description) { this.tariffDescription = description; }

    @ManyToMany(targetEntity = Option.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinTable(
            name = "availableOptions",
            joinColumns = @JoinColumn(name = "tariffId"),
            inverseJoinColumns = @JoinColumn(name = "optionId")
    )
    private Set<Option> options = new HashSet<>();

    public Set<Option> getOptions() {
        return options;
    }

    public void setOptions(Set<Option> options) {
        this.options = options;
    }

    public void addOption(Option option) {
        options.add(option);
        option.getTariffs().add(this);
    }

    public void removeOption(Option option) {
        options.remove(option);
        option.getTariffs().remove(this);
    }

    @OneToOne(mappedBy = "tariff")
    private Contract contract;

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
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
        String optionString = "";
        for (Option option : options) {
            optionString = optionString + option;
        }
        return "Tariff{" +
                "id=" + id +
                ", name='" + tariffName + '\'' +
                ", price=" + tariffPrice +
                ", description=" + tariffDescription +
                ", options=" + optionString +
                '}';
    }
}
