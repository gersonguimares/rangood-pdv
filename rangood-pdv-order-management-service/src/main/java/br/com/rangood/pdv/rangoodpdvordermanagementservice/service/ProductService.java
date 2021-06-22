package br.com.rangood.pdv.rangoodpdvordermanagementservice.service;

import br.com.rangood.pdv.rangoodpdvordermanagementservice.expections.NotFoundEntityFeignClientException;
import br.com.rangood.pdv.rangoodpdvordermanagementservice.expections.UnavailableServiceFeignClientException;
import br.com.rangood.pdv.rangoodpdvordermanagementservice.feignclient.productservice.Product;
import br.com.rangood.pdv.rangoodpdvordermanagementservice.feignclient.productservice.ProductServiceFeignClient;
import feign.RetryableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {


    final private ProductServiceFeignClient productServiceFeignClient;

    @Autowired
    public ProductService(ProductServiceFeignClient productServiceFeignClient) {
        this.productServiceFeignClient = productServiceFeignClient;
    }

    public Product findById(UUID id) throws NotFoundEntityFeignClientException,UnavailableServiceFeignClientException  {

        Product product = null;
        try {
            product = productServiceFeignClient.findById(id).getBody();

        }catch (RetryableException e) {
            throw new UnavailableServiceFeignClientException();
        }

        if (product == null) {
            throw new NotFoundEntityFeignClientException();
        }
        return product;
    }

    public List<Product> getAllProducts() throws UnavailableServiceFeignClientException  {

        try {
            final List<Product> productList = productServiceFeignClient.getAll().getBody();
            return productList;
        }catch (RetryableException e) {
            throw new UnavailableServiceFeignClientException();
        }
    }

}
