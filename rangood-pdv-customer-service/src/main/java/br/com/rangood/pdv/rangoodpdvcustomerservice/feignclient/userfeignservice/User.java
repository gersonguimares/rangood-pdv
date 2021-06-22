//TODO validate email format

package br.com.rangood.pdv.rangoodpdvcustomerservice.feignclient.userfeignservice;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class User implements Serializable {

    private static final Long serialVersionUID = 1l;

    private UUID id;
    private String name;
    private String username;
    private String password;
    private Set<Role> roles = new HashSet<>();

    public User() {}

    public User(String name, String username, String password) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.username = username;
        this.password = password;
    }

    @Id
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setUsername(String email) {
        this.username = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    public void addRole(Role role) {
        this.roles.add(role);
    }

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }
}
