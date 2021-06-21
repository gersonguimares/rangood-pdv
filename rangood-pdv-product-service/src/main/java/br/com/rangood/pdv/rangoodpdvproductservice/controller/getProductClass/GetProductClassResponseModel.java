package br.com.rangood.pdv.rangoodpdvproductservice.controller.getProductClass;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.UUID;

public class GetProductClassResponseModel {

    private String id;

    private String name;

    public GetProductClassResponseModel setId(String id) {
        this.id = id;
        return this;
    }

    public GetProductClassResponseModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static final GetProductClassResponseModel builder(String id, String name) {
        return (new GetProductClassResponseModel())
                .setId(id)
                .setName(name);
    }
}
