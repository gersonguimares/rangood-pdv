package br.com.rangood.pdv.rangoodpdvproductservice.controller.addProduct;

import br.com.rangood.pdv.rangoodpdvproductservice.controller.RestErrorHandler;
import br.com.rangood.pdv.rangoodpdvproductservice.controller.ValidationErrorHandler;
import br.com.rangood.pdv.rangoodpdvproductservice.model.Product;
import br.com.rangood.pdv.rangoodpdvproductservice.model.ProductClass;
import br.com.rangood.pdv.rangoodpdvproductservice.service.ProductClassService;
import br.com.rangood.pdv.rangoodpdvproductservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/product")
public class AddProduct {

    private final ProductService productService;
    private final ProductClassService productClassService;

    @Autowired
    public AddProduct(ProductService productService, ProductClassService productClassService) {
        this.productService = productService;
        this.productClassService = productClassService;
    }

    @PostMapping
    public ResponseEntity execute(@RequestBody @Valid final AddProductRequestModel addProductRequestModel, final BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()) {
            return ResponseEntity
                    .badRequest()
                    .body(ValidationErrorHandler.fromBindingErrors(bindingResult));
        }

        ProductClass productClass = productClassService.findById(addProductRequestModel.getProductClassId());
        if (productClass == null) {
            return RestErrorHandler.ResponseEntityErrorBuilder
                    .conflited(new String[]{"productClassId", "Product class not found"});
        }
        Product product = new Product(
                addProductRequestModel.getName(),
                addProductRequestModel.getPrice(),
                productClass,
                addProductRequestModel.isAvailableSale()
        );
        productService.addProduct(product);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new AddProductResponseModel(product.getId().toString()));
    }
}
