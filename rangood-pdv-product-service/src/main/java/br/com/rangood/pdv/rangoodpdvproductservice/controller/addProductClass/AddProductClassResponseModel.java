package br.com.rangood.pdv.rangoodpdvproductservice.controller.addProductClass;

public class AddProductClassResponseModel {

    private String id;

    public String getId() {
        return id;
    }

    public AddProductClassResponseModel setId(String id) {
        this.id = id;
        return this;
    }

    public static AddProductClassResponseModel builder(String id) {
        return (new AddProductClassResponseModel()).setId(id);
    }


}
