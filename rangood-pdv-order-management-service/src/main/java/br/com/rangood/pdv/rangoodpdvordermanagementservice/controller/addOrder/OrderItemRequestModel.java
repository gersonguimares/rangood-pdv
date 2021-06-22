package br.com.rangood.pdv.rangoodpdvordermanagementservice.controller.addOrder;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class OrderItemRequestModel {

    @NotNull
    private int quantity;
    @NotNull
    private UUID productId;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }
}