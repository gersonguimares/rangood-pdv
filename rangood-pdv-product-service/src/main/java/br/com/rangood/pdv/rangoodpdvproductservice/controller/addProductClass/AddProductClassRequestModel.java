package br.com.rangood.pdv.rangoodpdvproductservice.controller.addProductClass;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

public class AddProductClassRequestModel {

    @NotNull(message = "The product class name cannot be null")
    @NotEmpty
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
