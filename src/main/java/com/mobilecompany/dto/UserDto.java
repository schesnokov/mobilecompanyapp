package com.mobilecompany.dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

/**
 * The type User dto.
 */
public class UserDto implements Serializable {

    private int id;
    private String firstName;
    private String secondName;
    private Date dateOfBirth;
    private String passportNumber;
    private String adress;
    private String email;
    private String password;
    private RoleDto role;
    private Set<ContractDto> contracts;


    /**
     * Instantiates a new User dto.
     */
    public UserDto() {
    }

    /**
     * Instantiates a new User dto.
     *
     * @param contracts      the contracts
     * @param id             the id
     * @param firstName      the first name
     * @param secondName     the second name
     * @param dateOfBirth    the date of birth
     * @param passportNumber the passport number
     * @param adress         the adress
     * @param email          the email
     * @param password       the password
     */
    public UserDto(Set<ContractDto> contracts, int id, String firstName, String secondName, Date dateOfBirth, String passportNumber, String adress, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.dateOfBirth = dateOfBirth;
        this.passportNumber = passportNumber;
        this.adress = adress;
        this.email = email;
        this.password = password;
        this.contracts = contracts;
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
    public RoleDto getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(RoleDto role) {
        this.role = role;
    }


    /**
     * Gets contracts.
     *
     * @return the contracts
     */
    public Set<ContractDto> getContracts() {
        return contracts;
    }

    /**
     * Sets contracts.
     *
     * @param contracts the contracts
     */
    public void setContracts(Set<ContractDto> contracts) {
        this.contracts = contracts;
    }
}
