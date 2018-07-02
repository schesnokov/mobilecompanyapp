package com.mobilecompany.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Contracts {
    private int id;
    private String number;
    private BigDecimal balance;
    private byte isBlocked;
    private Users usersByClient;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "number", nullable = false, length = 12)
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Basic
    @Column(name = "balance", nullable = false, precision = 2)
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Basic
    @Column(name = "isBlocked", nullable = false)
    public byte getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(byte isBlocked) {
        this.isBlocked = isBlocked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contracts contracts = (Contracts) o;

        if (id != contracts.id) return false;
        if (isBlocked != contracts.isBlocked) return false;
        if (number != null ? !number.equals(contracts.number) : contracts.number != null) return false;
        if (balance != null ? !balance.equals(contracts.balance) : contracts.balance != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        result = 31 * result + (int) isBlocked;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "client", referencedColumnName = "id", nullable = false)
    public Users getUsersByClient() {
        return usersByClient;
    }

    public void setUsersByClient(Users usersByClient) {
        this.usersByClient = usersByClient;
    }
}
