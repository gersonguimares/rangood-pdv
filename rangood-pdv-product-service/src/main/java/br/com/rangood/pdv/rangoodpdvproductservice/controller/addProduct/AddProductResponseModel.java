package br.com.rangood.pdv.rangoodpdvproductservice.controller.addProduct;

public class AddProductResponseModel {

    public String id;

    public AddProductResponseModel(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
