package br.com.rangood.pdv.rangoodpdvusermanagementservice.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_ROLE")
public class Role implements Serializable {

    private static final Long serialVersionUID = 1l;

    private UUID id;
    private String roleName;

    public Role(){};

    public Role(String roleName) {
        this.id = UUID.randomUUID();
        this.roleName = roleName;
    }

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Id
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
