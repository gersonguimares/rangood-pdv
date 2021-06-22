package br.com.rangood.pdv.rangoodpdvcustomerservice.service;

import br.com.rangood.pdv.rangoodpdvcustomerservice.model.Customer;
import br.com.rangood.pdv.rangoodpdvcustomerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer addCustomer(final Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer getCustomerByEmail(final String email) {
        final Optional<Customer> customer = customerRepository.findById(email);
        if(customer.isEmpty()) {
            return null;
        } else {
            return  customer.get();
        }
    }

    public Customer getCustomerByUuid(final UUID uuid) {
        final Optional<Customer> customer = customerRepository.findByUuid(uuid);
        if(customer.isEmpty()) {
            return null;
        } else {
            return  customer.get();
        }
    }

    public List<Customer> getlAll() {
        return customerRepository.findAll();
    }
}
