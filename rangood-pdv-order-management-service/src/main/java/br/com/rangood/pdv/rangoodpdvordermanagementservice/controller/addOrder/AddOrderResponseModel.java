package br.com.rangood.pdv.rangoodpdvordermanagementservice.controller.addOrder;

import br.com.rangood.pdv.rangoodpdvordermanagementservice.model.OrderItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class AddOrderResponseModel {

    private UUID id;
    private String orderNumber;
    private List<OrderItem> itens;
    private BigDecimal orderAmount;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public List<OrderItem> getItens() {
        return itens;
    }

    public void setItens(List<OrderItem> itens) {
        this.itens = itens;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }
}
