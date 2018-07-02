package com.mobilecompany.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "tariffs")
public class Tariffs {
    private int id;
    private String name;
    private BigDecimal price;



    @ManyToMany
    @JoinTable(name = "availableoptions",
            joinColumns = @JoinColumn(name = "tariffId"),
            inverseJoinColumns = @JoinColumn(name = "optionId"))
    private List<Option> options;

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 15)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "price", nullable = true, precision = 2)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tariffs tariffs = (Tariffs) o;

        if (id != tariffs.id) return false;
        if (name != null ? !name.equals(tariffs.name) : tariffs.name != null) return false;
        if (price != null ? !price.equals(tariffs.price) : tariffs.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
