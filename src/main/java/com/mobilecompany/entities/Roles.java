package com.mobilecompany.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Roles")
@Table(name="roles")
public class Roles {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    public Roles() {
    }

    public Roles(int id, String name) {
        this.id = id;
        this.name = name;
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

    @OneToMany(mappedBy = "roles", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Users> users = new HashSet<>();

    public Set<Users> getUsers() {
        return this.users;
    }

    public void setUsers(Set<Users> users) {
        this.users = users;
    }

    public void addUsers(Users users) {
        users.setRoles(this);
        getUsers().add(users);
    }

    public void removeUsers(Users users) {
        getUsers().remove(users);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Roles roles = (Roles) o;

        if (id != roles.id) return false;
        if (name != null ? !name.equals(roles.name) : roles.name != null) return false;

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
        return "Roles{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", users=" + users +
                '}';
    }
}
