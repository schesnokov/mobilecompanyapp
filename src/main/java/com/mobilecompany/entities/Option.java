package com.mobilecompany.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Option")
@Table(name="options")
public class Option {

    @Id
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="price")
    private BigDecimal price;

    @Column(name="connectionCost")
    private BigDecimal connectionCost;

    public Option() {}

    public Option(int id, String name, BigDecimal price, BigDecimal connectionCost) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.connectionCost = connectionCost;
    }

    @ManyToMany(cascade = CascadeType.ALL,
                targetEntity = Tariffs.class,
                mappedBy = "options")
    private Set<Tariffs> tariffs = new HashSet<>();

    public Set<Tariffs> getTariffs() {
        return tariffs;
    }

    public void setTariffs(Set<Tariffs> tariffs) {
        this.tariffs = tariffs;
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

    @Override
    public String toString() {
        return "Option{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", connectionCost=" + connectionCost +
                '}';
    }
}
