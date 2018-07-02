package com.mobilecompany.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name="options")
public class Option {
    @Id
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="price")
    private BigDecimal price;

    @Column(name="connectionPrice")
    private BigDecimal connectionCost;

    @ManyToMany
    @JoinTable(name = "availableoptions",
            joinColumns = @JoinColumn(name = "optionId"),
            inverseJoinColumns = @JoinColumn(name = "tariffId"))
    private List<Tariffs> tariffs;

    public List<Tariffs> getTariffs() {
        return tariffs;
    }

    public void setTariffs(List<Tariffs> tariffs) {
        this.tariffs = tariffs;
    }

    public Option() {}

    public Option(int id, String name, BigDecimal price, BigDecimal connectionCost) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.connectionCost = connectionCost;
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
}
