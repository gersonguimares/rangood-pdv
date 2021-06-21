package br.com.rangood.pdv.rangoodpdvproductservice.controller.addProductClass;

import br.com.rangood.pdv.rangoodpdvproductservice.controller.RestErrorHandler;
import br.com.rangood.pdv.rangoodpdvproductservice.controller.ValidationErrorHandler;
import br.com.rangood.pdv.rangoodpdvproductservice.model.ProductClass;
import br.com.rangood.pdv.rangoodpdvproductservice.service.ProductClassService;
import br.com.rangood.pdv.rangoodpdvproductservice.service.ProductService;
import br.com.rangood.pdv.rangoodpdvproductservice.service.exceptions.EntityAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/product/class")
public class AddProductClass {

    private final ProductClassService productClassService;

    @Autowired
    public AddProductClass(ProductClassService productClassService) {
        this.productClassService = productClassService;
    }

    @PostMapping
    public ResponseEntity execute(@RequestBody @Valid AddProductClassRequestModel addProductClassRequestModel, final BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()) {
            return ResponseEntity
                    .badRequest()
                    .body(ValidationErrorHandler.fromBindingErrors(bindingResult));
        }

        ProductClass productClass = null;
        try {
            productClass = productClassService.add(new ProductClass(addProductClassRequestModel.getName()));
        } catch (EntityAlreadyExistException e) {
            return RestErrorHandler.ResponseEntityErrorBuilder
                    .conflited(new String[]{"name", "There is already a product class with this name"});
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(AddProductClassResponseModel
                        .builder(productClass.getId().toString())
                );

    }
}
