package br.com.rangood.pdv.rangoodpdvordermanagementservice.feignclient.customerservice;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import java.time.LocalDate;

public class Customer {

    private String name;
    private LocalDate birthDate;
    private String phoneNumber;
    private String email;

    public Customer(String name, LocalDate birthDate, String phoneNumber, String email) {
        this.name = name;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Customer() {}

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
