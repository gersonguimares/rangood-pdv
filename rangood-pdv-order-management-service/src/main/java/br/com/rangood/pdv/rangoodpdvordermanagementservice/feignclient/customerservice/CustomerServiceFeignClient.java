package br.com.rangood.pdv.rangoodpdvordermanagementservice.feignclient.customerservice;

import br.com.rangood.pdv.rangoodpdvordermanagementservice.feignclient.productservice.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Component
@FeignClient(name = "rangood-pdv-customer", path = "/customer/Forservice")
public interface CustomerServiceFeignClient {

    @GetMapping
    ResponseEntity<Customer> getByUUid(@RequestParam UUID uuid);


}
