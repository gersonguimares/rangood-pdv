package br.com.rangood.pdv.rangoodpdvordermanagementservice.feignclient.productservice;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {

    private UUID id;
    private String name;
    private BigDecimal price;
    private boolean availableSale;
    private ProductClass productClass;

    public Product(){}

    public Product(String name, BigDecimal price, ProductClass productClass, boolean availableSale) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.price = price;
        this.productClass = productClass;
        this.availableSale = availableSale;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ProductClass getProductClass() {
        return productClass;
    }

    public void setProductClass(ProductClass productClass) {
        this.productClass = productClass;
    }

    public boolean isAvailableSale() {
        return availableSale;
    }

    public void setAvailableSale(boolean availableSale) {
        this.availableSale = availableSale;
    }
}
