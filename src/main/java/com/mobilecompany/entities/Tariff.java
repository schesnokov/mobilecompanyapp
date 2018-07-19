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
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "description")
    private String description;

    public Tariff() {}

    public Tariff(int id, String name, BigDecimal price, Set<Option> options) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.options = options;
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

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

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
        if (name != null ? !name.equals(tariff.name) : tariff.name != null) return false;
        if (price != null ? !price.equals(tariff.price) : tariff.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
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
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description=" + description +
                ", options=" + optionString +
                '}';
    }
}