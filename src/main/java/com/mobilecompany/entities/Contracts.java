package com.mobilecompany.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "Contracts")
@Table(name = "contracts")
public class Contracts {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "number")
    private String number;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "isBlocked")
    private byte isBlocked;

    public Contracts() {
    }

    public Contracts(int id, String number, BigDecimal balance, byte isBlocked) {
        this.id = id;
        this.number = number;
        this.balance = balance;
        this.isBlocked = isBlocked;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }

    public BigDecimal getBalance() {
        return balance;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public byte getIsBlocked() {
        return isBlocked;
    }
    public void setIsBlocked(byte isBlocked) {
        this.isBlocked = isBlocked;
    }

    @ManyToOne
    @JoinColumn(name = "client")
    private Users users;

    public Users getUsers() {
        return this.users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tariff")
    private Tariffs tariff;

    public Tariffs getTariff() {
        return tariff;
    }

    public void setTariff(Tariffs tariff) {
        this.tariff = tariff;
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

    @Override
    public String toString() {
        return "Contracts{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", balance=" + balance +
                ", isBlocked=" + isBlocked +
                ", users=" + users +
                ", tariff=" + tariff +
                '}';
    }
}
