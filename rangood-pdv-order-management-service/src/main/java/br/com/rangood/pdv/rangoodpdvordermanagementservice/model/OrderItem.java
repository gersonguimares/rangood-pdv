package br.com.rangood.pdv.rangoodpdvordermanagementservice.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "TB_ORDER_ITEM")
public class OrderItem {

    @Id
    private UUID id;
    private BigDecimal amount;
    private int quantity;
    private UUID productId;

    public OrderItem(){}

    public OrderItem(BigDecimal amount, int quantity,UUID productId) {
        this.id = UUID.randomUUID();
        this.amount = amount;
        this.quantity = quantity;
        this.productId = productId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

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
