package br.com.rangood.pdv.rangoodpdvproductservice.controller.updateProduct;

import br.com.rangood.pdv.rangoodpdvproductservice.controller.RestErrorHandler;
import br.com.rangood.pdv.rangoodpdvproductservice.controller.ValidationErrorHandler;
import br.com.rangood.pdv.rangoodpdvproductservice.controller.updateProductClass.UpdateProductClassRequestModel;
import br.com.rangood.pdv.rangoodpdvproductservice.model.Product;
import br.com.rangood.pdv.rangoodpdvproductservice.model.ProductClass;
import br.com.rangood.pdv.rangoodpdvproductservice.service.ProductService;
import br.com.rangood.pdv.rangoodpdvproductservice.service.exceptions.EntityAlreadyExistException;
import br.com.rangood.pdv.rangoodpdvproductservice.service.exceptions.NotFoundEntityException;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class UptadeProduct {

    private final ProductService productService;

    @Autowired
    public UptadeProduct(ProductService productService) {
        this.productService = productService;
    }

    @PatchMapping("/{id}")
    public ResponseEntity pathById(@PathVariable UUID id, @RequestBody JsonNode updateData){

        Product product = productService.findById(id);
        if (product == null) {
            return ResponseEntity
                    .notFound()
                    .build();
        }

        try {
            if(updateData.get("name") != null) {
                product.setName(updateData.get("name").textValue());
            }
            if(updateData.get("price") != null) {
                product.setPrice(updateData.get("price").decimalValue());
            }
            if(updateData.get("availableSale") != null) {
                product.setAvailableSale(updateData.get("availableSale").booleanValue());
            }
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .build();
        }

        try {
            productService.update(product);
        } catch (NotFoundEntityException e) {
            return ResponseEntity
                    .notFound()
                    .build();
        } catch (EntityAlreadyExistException e) {
            return RestErrorHandler.ResponseEntityErrorBuilder
                    .conflited(new String[]{"name", "There is already a product class with this name"});
        }

        return ResponseEntity.noContent().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity putById(@PathVariable UUID id, @RequestBody @Valid UpdateProductRequestModel updateProductRequestModel, final BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return ResponseEntity
                    .badRequest()
                    .body(ValidationErrorHandler.fromBindingErrors(bindingResult));
        }

        Product product = productService.findById(id);
        if (product == null) {
            return ResponseEntity
                    .notFound()
                    .build();
        }

        product.setName(updateProductRequestModel.getName());
        product.setPrice(updateProductRequestModel.getPrice());
        product.setAvailableSale(updateProductRequestModel.isAvailableSale());

        try {
            productService.update(product);
        } catch (NotFoundEntityException e) {
            return ResponseEntity
                    .notFound()
                    .build();
        } catch (EntityAlreadyExistException e) {}

        return ResponseEntity.noContent().build();
    }
}
