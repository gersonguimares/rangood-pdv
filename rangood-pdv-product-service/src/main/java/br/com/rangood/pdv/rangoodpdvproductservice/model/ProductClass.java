package br.com.rangood.pdv.rangoodpdvproductservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "TB_PRODUCT_CLASS")
public class ProductClass {

    @Id
    private UUID id;

    @Column(unique = true)
    private String name;

    public ProductClass(){}

    public ProductClass(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID uuid) {
        this.id = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
