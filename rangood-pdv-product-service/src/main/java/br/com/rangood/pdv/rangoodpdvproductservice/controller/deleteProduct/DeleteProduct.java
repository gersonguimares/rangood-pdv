package br.com.rangood.pdv.rangoodpdvproductservice.controller.deleteProduct;

import br.com.rangood.pdv.rangoodpdvproductservice.controller.RestErrorHandler;
import br.com.rangood.pdv.rangoodpdvproductservice.service.ProductService;
import br.com.rangood.pdv.rangoodpdvproductservice.service.exceptions.NotFoundEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("product")
public class DeleteProduct {

    private final ProductService productService;
    @Autowired
    public DeleteProduct(ProductService productService) {
        this.productService = productService;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable UUID id) {

        //TODO Verificar dependecia de registro com o serviço order-management

        boolean isDeleted = false;
        try {
            isDeleted = productService.deleteById(id);
        } catch (NotFoundEntityException e) {
            return ResponseEntity.notFound().build();
        }

        if (isDeleted == false) {
            return RestErrorHandler.ResponseEntityErrorBuilder.conflited(
                    new String[]{"id", "It was not possible to delete, as this product class is being used by other records "}
            );
        }

        return ResponseEntity.noContent().build();
    }
}
