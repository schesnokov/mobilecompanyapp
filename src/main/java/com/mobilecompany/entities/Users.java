package com.mobilecompany.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity(name = "Users")
@Table(name="users")
public class Users {

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

    @Column(name = "isBlocked")
    private byte isBlocked;

    public Users() {
    }

    public Users(int id, String firstName, String secondName, Date dateOfBirth, String passportNumber, String adress, String email, String password, byte isBlocked) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.dateOfBirth = dateOfBirth;
        this.passportNumber = passportNumber;
        this.adress = adress;
        this.email = email;
        this.password = password;
        this.isBlocked = isBlocked;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPassportNumber() { return passportNumber; }
    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getAdress() {
        return adress;
    }
    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public byte getIsBlocked() {
        return isBlocked;
    }
    public void setIsBlocked(byte isBlocked) {
        this.isBlocked = isBlocked;
    }

    @ManyToOne
    @JoinColumn(name = "role")
    private Roles roles;

    public Roles getRoles() {
        return this.roles;
    }

    public void setRoles(Roles role) {
        this.roles = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Users users = (Users) o;

        if (id != users.id) return false;
        if (isBlocked != users.isBlocked) return false;
        if (firstName != null ? !firstName.equals(users.firstName) : users.firstName != null) return false;
        if (secondName != null ? !secondName.equals(users.secondName) : users.secondName != null) return false;
        if (dateOfBirth != null ? !dateOfBirth.equals(users.dateOfBirth) : users.dateOfBirth != null) return false;
        if (passportNumber != null ? !passportNumber.equals(users.passportNumber) : users.passportNumber != null)
            return false;
        if (adress != null ? !adress.equals(users.adress) : users.adress != null) return false;
        if (email != null ? !email.equals(users.email) : users.email != null) return false;
        if (password != null ? !password.equals(users.password) : users.password != null) return false;

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
        result = 31 * result + (int) isBlocked;
        return result;
    }

    @Override
    public String toString() {
        return "Users{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", passportNumber='" + passportNumber + '\'' +
                ", adress='" + adress + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isBlocked=" + isBlocked +
                ", roles=" + roles.getName() +
                '}';
    }
}
