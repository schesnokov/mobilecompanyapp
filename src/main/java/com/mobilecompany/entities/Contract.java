package com.mobilecompany.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity(name = "Contract")
@Table(name = "contracts")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "number")
    private String number;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "isBlocked")
    private int isBlocked;

    @ManyToOne
    @JoinColumn(name = "clientId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "tariffId")
    private Tariff tariff;

    @JoinTable(name = "selectedoptions", joinColumns = {
            @JoinColumn(name = "contractId", referencedColumnName = "id", nullable = false)},
            inverseJoinColumns = {
            @JoinColumn(name = "optionId", referencedColumnName = "id", nullable = false)
    })
    @ManyToMany
    private Set<Option> selectedOptions;

    public Contract() {
    }

    public Contract(int id, String number, BigDecimal balance, byte isBlocked) {
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

    public int getIsBlocked() {
        return isBlocked;
    }
    public void setIsBlocked(int isBlocked) {
        this.isBlocked = isBlocked;
    }

    public User getUser() {
        return this.user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Tariff getTariff() {
        return tariff;
    }
    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    public Set<Option> getSelectedOptions() {
        return selectedOptions;
    }
    public void setSelectedOptions(Set<Option> selectedOptions) {
        this.selectedOptions = selectedOptions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contract contract = (Contract) o;

        if (id != contract.id) return false;
        if (isBlocked != contract.isBlocked) return false;
        if (number != null ? !number.equals(contract.number) : contract.number != null) return false;
        if (balance != null ? !balance.equals(contract.balance) : contract.balance != null) return false;

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
        return "Contract{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", balance=" + balance +
                ", isBlocked=" + isBlocked +
                ", user=" + user +
                ", tariff=" + tariff +
                '}';
    }
}
