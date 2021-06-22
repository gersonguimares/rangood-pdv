package br.com.rangood.pdv.rangoodpdvordermanagementservice.controller.addOrder;

import br.com.rangood.pdv.rangoodpdvordermanagementservice.model.OrderItem;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class AddOrderRequestModel {

    @NotNull
    private UUID customerId;
    @NotNull
    private List<OrderItemRequestModel> itens;

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public List<OrderItemRequestModel> getItens() {
        return itens;
    }

    public void setItens(List<OrderItemRequestModel> itens) {
        this.itens = itens;
    }

}
