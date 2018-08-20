package com.mobilecompany.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * The type Role.
 */
@Entity(name = "Role")
@Table(name = "roles")
public class Role {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "roleName")
    private String name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<User> users = new HashSet<>();

    /**
     * Instantiates a new Role.
     */
    public Role() {
    }

    /**
     * Instantiates a new Role.
     *
     * @param id   the id
     * @param name the name
     */
    public Role(int id, String name) {
        this.id = id;
        this.name = name;
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
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets users.
     *
     * @return the users
     */
    public Set<User> getUsers() {
        return this.users;
    }

    /**
     * Sets users.
     *
     * @param users the users
     */
    public void setUsers(Set<User> users) {
        this.users = users;
    }

    /**
     * Add users.
     *
     * @param user the user
     */
    public void addUsers(User user) {
        user.setRole(this);
        getUsers().add(user);
    }

    /**
     * Remove users.
     *
     * @param user the user
     */
    public void removeUsers(User user) {
        getUsers().remove(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (id != role.id) return false;
        if (name != null ? !name.equals(role.name) : role.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", users=" + users +
                '}';
    }
}
