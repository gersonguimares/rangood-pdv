package br.com.rangood.pdv.rangoodpdvordermanagementservice.model;

import jdk.jfr.Event;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TB_ORDER")
public class Order {

    @Id
    private UUID id;
    private String numberOrder;
    private LocalDateTime date;
    private UUID requesterId;
    private String status;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="order_id")
    private List<OrderEvent> events = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="order_id")
    private List<OrderItem> itens = new ArrayList<>();

    public Order(String numberOrder, LocalDateTime orderTime) {
        this.id = UUID.randomUUID();
        this.numberOrder = numberOrder;
        this.date = orderTime;
    }

    public Order() {

    }

    public UUID getId() {
        return id;
    }

    public String getNumberOrder() {
        return numberOrder;
    }

    public void setNumberOrder(String numberOrder) {
        this.numberOrder = numberOrder;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public UUID getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(UUID requesterId) {
        this.requesterId = requesterId;
    }

    public List<OrderEvent> getEvents() {
        return events;
    }

    public void setEvents(List<OrderEvent> events) {
        this.events = events;
    }

    public List<OrderItem> getItens() {
        return itens;
    }

    public void setItens(List<OrderItem> itens) {
        this.itens = itens;
    }

    public void addOrderEvent(OrderEvent orderEvent) {
        this.events.add(orderEvent);
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
