package br.com.rangood.pdv.rangoodpdvproductservice.controller.updateProductClass;

import br.com.rangood.pdv.rangoodpdvproductservice.controller.RestErrorHandler;
import br.com.rangood.pdv.rangoodpdvproductservice.controller.ValidationErrorHandler;
import br.com.rangood.pdv.rangoodpdvproductservice.model.ProductClass;
import br.com.rangood.pdv.rangoodpdvproductservice.service.ProductClassService;
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
@RequestMapping("/product/class")
public class UptadeProcutClass {

    private final ProductClassService productClassService;

    @Autowired
    public UptadeProcutClass(ProductClassService productClassService) {
        this.productClassService = productClassService;
    }

    @PatchMapping("/{id}")
    public ResponseEntity pathById(@PathVariable UUID id, @RequestBody JsonNode updateData){

        ProductClass productClass = productClassService.findById(id);
        if (productClass == null) {
            return ResponseEntity
                    .notFound()
                    .build();
        }

        try {
            if(updateData.get("name") != null) {
                productClass.setName(updateData.get("name").textValue());
            }
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .build();
        }

        try {
            productClassService.update(productClass);
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
    public ResponseEntity putById(@PathVariable UUID id, @RequestBody @Valid UpdateProductClassRequestModel updateProductClassRequestModel, final BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return ResponseEntity
                    .badRequest()
                    .body(ValidationErrorHandler.fromBindingErrors(bindingResult));
        }

        try {
            ProductClass productClass = new ProductClass();
            productClass.setName(updateProductClassRequestModel.getName());
            productClass.setId(id);
            productClassService.update(productClass);
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
}
