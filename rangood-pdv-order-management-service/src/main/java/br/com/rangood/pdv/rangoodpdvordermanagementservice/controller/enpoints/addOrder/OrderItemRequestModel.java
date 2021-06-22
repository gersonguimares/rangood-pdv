package br.com.rangood.pdv.rangoodpdvordermanagementservice.controller.enpoints.addOrder;

import javax.validation.constraints.NotNull;

public class OrderItemRequestModel {

    @NotNull
    private int quantity;

    @NotNull
    private String productId;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
