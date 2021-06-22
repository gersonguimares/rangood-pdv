package br.com.rangood.pdv.rangoodpdvcustomerservice.controller.registerCustomer;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AddCustomerRequestModel {

    @NotNull(message = "The customer name cannot be blank")
    private String name;

    @NotNull(message = "The customer's date of birth cannot be blank and must have the format DD/MM/YYYY")
    private LocalDate birthDate;

    @NotNull(message = "The customer's date of birth cannot be blank and must have the format DD/MM/YYYY")
    private String phoneNumber;

    @NotNull(message = "The customer's email cannot be blank")
    private String email;

    @NotNull(message = "The customer's password cannot be blank")
    private String password;

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
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
