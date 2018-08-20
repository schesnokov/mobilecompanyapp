package com.mobilecompany.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * The type User.
 */
@Entity(name = "User")
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "secondName")
    private String secondName;

    @Column(name = "dateOfBirth")
    private Date dateOfBirth;

    @Column(name = "passportNumber")
    private String passportNumber;

    @Column(name = "adress")
    private String adress;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Contract> contracts = new HashSet<>();

    /**
     * Instantiates a new User.
     */
    public User() {
    }

    /**
     * Instantiates a new User.
     *
     * @param id             the id
     * @param firstName      the first name
     * @param secondName     the second name
     * @param dateOfBirth    the date of birth
     * @param passportNumber the passport number
     * @param adress         the adress
     * @param email          the email
     * @param password       the password
     */
    public User(int id, String firstName, String secondName, Date dateOfBirth, String passportNumber, String adress, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.dateOfBirth = dateOfBirth;
        this.passportNumber = passportNumber;
        this.adress = adress;
        this.email = email;
        this.password = password;
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
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets second name.
     *
     * @return the second name
     */
    public String getSecondName() {
        return secondName;
    }

    /**
     * Sets second name.
     *
     * @param secondName the second name
     */
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    /**
     * Gets date of birth.
     *
     * @return the date of birth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets date of birth.
     *
     * @param dateOfBirth the date of birth
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Gets passport number.
     *
     * @return the passport number
     */
    public String getPassportNumber() {
        return passportNumber;
    }

    /**
     * Sets passport number.
     *
     * @param passportNumber the passport number
     */
    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    /**
     * Gets adress.
     *
     * @return the adress
     */
    public String getAdress() {
        return adress;
    }

    /**
     * Sets adress.
     *
     * @param adress the adress
     */
    public void setAdress(String adress) {
        this.adress = adress;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public Role getRole() {
        return this.role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(Role role) {
        this.role = role;
    }


    /**
     * Gets contracts.
     *
     * @return the contracts
     */
    public Set<Contract> getContracts() {
        return this.contracts;
    }

    /**
     * Sets contracts.
     *
     * @param contracts the contracts
     */
    public void setContracts(Set<Contract> contracts) {
        this.contracts = contracts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (secondName != null ? !secondName.equals(user.secondName) : user.secondName != null) return false;
        if (dateOfBirth != null ? !dateOfBirth.equals(user.dateOfBirth) : user.dateOfBirth != null) return false;
        if (passportNumber != null ? !passportNumber.equals(user.passportNumber) : user.passportNumber != null)
            return false;
        if (adress != null ? !adress.equals(user.adress) : user.adress != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        result = 31 * result + (passportNumber != null ? passportNumber.hashCode() : 0);
        result = 31 * result + (adress != null ? adress.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", passportNumber='" + passportNumber + '\'' +
                ", adress='" + adress + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role.getName() +
                '}';
    }
}
