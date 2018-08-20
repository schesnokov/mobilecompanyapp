package com.mobilecompany.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

/**
 * The type Contract.
 */
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

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "tariffId")
    private Tariff tariff;

    @JoinTable(name = "selectedoptions", joinColumns = {
            @JoinColumn(name = "contractId", referencedColumnName = "id", nullable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "optionId", referencedColumnName = "id", nullable = false)
            })
    @ManyToMany
    private Set<Option> selectedOptions;

    /**
     * Instantiates a new Contract.
     */
    public Contract() {
    }

    /**
     * Instantiates a new Contract.
     *
     * @param id        the id
     * @param number    the number
     * @param balance   the balance
     * @param isBlocked the is blocked
     */
    public Contract(int id, String number, BigDecimal balance, byte isBlocked) {
        this.id = id;
        this.number = number;
        this.balance = balance;
        this.isBlocked = isBlocked;
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
     * Gets number.
     *
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * Sets number.
     *
     * @param number the number
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Gets balance.
     *
     * @return the balance
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * Sets balance.
     *
     * @param balance the balance
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * Gets is blocked.
     *
     * @return the is blocked
     */
    public int getIsBlocked() {
        return isBlocked;
    }

    /**
     * Sets is blocked.
     *
     * @param isBlocked the is blocked
     */
    public void setIsBlocked(int isBlocked) {
        this.isBlocked = isBlocked;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return this.user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets tariff.
     *
     * @return the tariff
     */
    public Tariff getTariff() {
        return tariff;
    }

    /**
     * Sets tariff.
     *
     * @param tariff the tariff
     */
    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    /**
     * Gets selected options.
     *
     * @return the selected options
     */
    public Set<Option> getSelectedOptions() {
        return selectedOptions;
    }

    /**
     * Sets selected options.
     *
     * @param selectedOptions the selected options
     */
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
