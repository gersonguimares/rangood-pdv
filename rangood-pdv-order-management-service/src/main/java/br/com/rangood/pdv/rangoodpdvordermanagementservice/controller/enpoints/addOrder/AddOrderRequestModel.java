package br.com.rangood.pdv.rangoodpdvordermanagementservice.controller.enpoints.addOrder;

import javax.validation.constraints.NotNull;
import java.util.List;

public class AddOrderRequestModel {

    @NotNull
    private String requesterId;

    @NotNull(message = "The order item list cannot be empty")
    private List<OrderItemRequestModel> itens;

    public String getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(String requesterId) {
        this.requesterId = requesterId;
    }

    public List<OrderItemRequestModel> getItens() {
        return itens;
    }

    public void setItens(List<OrderItemRequestModel> itens) {
        this.itens = itens;
    }
}
