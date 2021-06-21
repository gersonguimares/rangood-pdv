package br.com.rangood.pdv.rangoodpdvproductservice.controller.getProductClass;

import br.com.rangood.pdv.rangoodpdvproductservice.model.ProductClass;
import br.com.rangood.pdv.rangoodpdvproductservice.service.ProductClassService;
import br.com.rangood.pdv.rangoodpdvproductservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product/class")
public class GetProductClass {

    private final ProductClassService productClassService;

    @Autowired
    public GetProductClass(ProductClassService productClassService) {
        this.productClassService = productClassService;
    }

    @GetMapping
    public ResponseEntity getAll(){
        final List<ProductClass> productClassList = productClassService.getlAll();
        if (productClassList == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(productClassList);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable UUID id){

        final ProductClass productClass = productClassService.findById(id);
        if (productClass == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(
                GetProductClassResponseModel.builder(
                        productClass.getId().toString(),
                        productClass.getName()
                )
            );
    }
}
