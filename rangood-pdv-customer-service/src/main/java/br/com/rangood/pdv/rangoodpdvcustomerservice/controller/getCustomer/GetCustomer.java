package br.com.rangood.pdv.rangoodpdvcustomerservice.controller.getCustomer;

import br.com.rangood.pdv.rangoodpdvcustomerservice.model.Customer;
import br.com.rangood.pdv.rangoodpdvcustomerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/customer")
public class GetCustomer {

    private final CustomerService customerService;

    @Autowired
    public GetCustomer(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity get() {
       return ResponseEntity.ok(customerService.getlAll());
    }

    @GetMapping("/{email}")
    public ResponseEntity getById(@PathVariable String email) {

        final Customer customer = customerService.getCustomerByEmail(email);
        if(customer == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(customer);
    }

    @GetMapping("/Forservice")
    public ResponseEntity getByUUid(@RequestParam String uuid) {

        final Customer customer = customerService.getCustomerByUuid(UUID.fromString(uuid));
        if(customer == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(customer);
    }

}
