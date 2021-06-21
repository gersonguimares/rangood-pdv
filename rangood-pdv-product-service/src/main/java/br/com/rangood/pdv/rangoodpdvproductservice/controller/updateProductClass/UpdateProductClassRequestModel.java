package br.com.rangood.pdv.rangoodpdvproductservice.controller.updateProductClass;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UpdateProductClassRequestModel {

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
