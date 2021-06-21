package br.com.rangood.pdv.rangoodpdvproductservice.controller.getProduct;

import br.com.rangood.pdv.rangoodpdvproductservice.model.Product;
import br.com.rangood.pdv.rangoodpdvproductservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class GetProduct {

    private final ProductService productService;

    @Autowired
    public GetProduct(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity getAll(){
        final List<Product> productClassList = productService.getlAll();
        if (productClassList == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(productClassList);
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable UUID id){

        final Product product = productService.findById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(product);
    }

}
