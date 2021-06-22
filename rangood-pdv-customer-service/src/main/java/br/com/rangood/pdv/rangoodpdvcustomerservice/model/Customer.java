package br.com.rangood.pdv.rangoodpdvcustomerservice.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "TB_CUSTOMER")
public class Customer {

    private UUID uuid;
    private String name;
    private LocalDate birthDate;
    private String phoneNumber;

    @Id
    @Email
    private String email;

    public Customer(String name, LocalDate birthDate, String phoneNumber, String email) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Customer() {}

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        phoneNumber = phoneNumber.replaceAll("[\\D]","");
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
