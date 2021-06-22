package br.com.rangood.pdv.rangoodpdvordermanagementservice.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

//TODO Implements behavior contract
@Entity
@Table(name = "TB_ORDER_EVENT")
public class OrderEvent {

    @Id
    private UUID id;
    private String type;
    private String note;
    private LocalDateTime date;

    public OrderEvent() {

    }

    public OrderEvent(String type, String note, LocalDateTime date) {
        this.id = UUID.randomUUID();
        this.type = type;
        this.note = note;
        this.date = date;
    }

    public UUID getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public enum Type {
        REQUESTED,
        CONFIRMED,
        PREPARING,
        AVAILABLE_WITHDRAWAL,
        AVAILABLE_SHIPPING,
        SENT,
        DELIVERED,
        RETURNED,
        CANCELED
    }

    public void setId(UUID id) {
        this.id = id;
    }

}
