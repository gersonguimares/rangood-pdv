package br.com.rangood.pdv.rangoodpdvproductservice.controller.addProduct;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class AddProductRequestModel {

    @NotNull(message = "The product name cannot be blank")
    private String name;

    @NotNull(message = "The product class id's  cannot be blank and must have the format UUID v.4")
    private UUID productClassId;

    @NotNull(message = "The product price cannot be blank, must have USD format and must be greater than zero")
    private BigDecimal price;

    private boolean availableSale;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getProductClassId() {
        return productClassId;
    }

    public void setProductClassId(UUID productClassId) {
        this.productClassId = productClassId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isAvailableSale() {
        return availableSale;
    }

    public void setAvailableSale(boolean availableSale) {
        this.availableSale = availableSale;
    }
}
