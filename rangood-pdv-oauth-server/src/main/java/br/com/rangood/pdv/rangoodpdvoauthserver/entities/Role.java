package br.com.rangood.pdv.rangoodpdvoauthserver.entities;

import java.io.Serializable;
import java.util.UUID;

public class Role implements Serializable {

    private static final Long serialVersionUID = 1l;

    private UUID id;
    private String roleName;

    public Role(){};

    public Role(UUID id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public UUID getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
