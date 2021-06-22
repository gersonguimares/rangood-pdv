package br.com.rangood.pdv.rangoodpdvcustomerservice.controller.registerCustomer;

public class AddCustomerResponseModel {

    public String id;

    public AddCustomerResponseModel(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
