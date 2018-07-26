package com.mobilecompany.dto;

import java.sql.Date;
import java.util.Set;

public class UserDto {

    private int id;
    private String firstName;
    private String secondName;
    private Date dateOfBirth;
    private String passportNumber;
    private String adress;
    private String email;
    private String password;
    private int isBlocked;
    private RoleDto role;
    private Set<ContractDto> contracts;


    public UserDto() {
    }

    public UserDto(Set<ContractDto> contracts, int id, String firstName, String secondName, Date dateOfBirth, String passportNumber, String adress, String email, String password, int isBlocked) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.dateOfBirth = dateOfBirth;
        this.passportNumber = passportNumber;
        this.adress = adress;
        this.email = email;
        this.password = password;
        this.isBlocked = isBlocked;
        this.contracts = contracts;
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

    public String getPassportNumber() {
        return passportNumber;
    }
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

    public int getIsBlocked() {
        return isBlocked;
    }
    public void setIsBlocked(int isBlocked) {
        this.isBlocked = isBlocked;
    }


    public RoleDto getRole() {
        return role;
    }
    public void setRole(RoleDto role) {
        this.role = role;
    }


    public Set<ContractDto> getContracts() {
        return contracts;
    }
    public void setContracts(Set<ContractDto> contracts) {
        this.contracts = contracts;
    }
}
