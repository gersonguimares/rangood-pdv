package br.com.rangood.pdv.rangoodpdvordermanagementservice.feignclient.productservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@Component
@FeignClient(name = "rangood-pdv-product", path = "/product")
public interface ProductServiceFeignClient {

    @GetMapping("/{id}")
    ResponseEntity<Product> findById(@PathVariable UUID id);

    @GetMapping
    public ResponseEntity<List<Product>> getAll();

}
