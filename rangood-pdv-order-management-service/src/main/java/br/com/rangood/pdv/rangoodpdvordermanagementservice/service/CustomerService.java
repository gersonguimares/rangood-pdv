package br.com.rangood.pdv.rangoodpdvordermanagementservice.service;

import br.com.rangood.pdv.rangoodpdvordermanagementservice.expections.NotFoundEntityFeignClientException;
import br.com.rangood.pdv.rangoodpdvordermanagementservice.expections.UnavailableServiceFeignClientException;
import br.com.rangood.pdv.rangoodpdvordermanagementservice.feignclient.customerservice.Customer;
import br.com.rangood.pdv.rangoodpdvordermanagementservice.feignclient.customerservice.CustomerServiceFeignClient;
import br.com.rangood.pdv.rangoodpdvordermanagementservice.feignclient.productservice.Product;
import br.com.rangood.pdv.rangoodpdvordermanagementservice.feignclient.productservice.ProductServiceFeignClient;
import feign.RetryableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {

    final private CustomerServiceFeignClient customerServiceFeignClient;

    @Autowired
    public CustomerService(CustomerServiceFeignClient customerServiceFeignClient) {
        this.customerServiceFeignClient = customerServiceFeignClient;
    }

    public Customer findById(UUID id) throws NotFoundEntityFeignClientException,UnavailableServiceFeignClientException  {

        Customer customer = null;
        try {
            customer = customerServiceFeignClient.getByUUid(id).getBody();
        }catch (RetryableException e) {
            throw new UnavailableServiceFeignClientException();
        }

        if (customer == null) {
            throw new NotFoundEntityFeignClientException();
        }
        return customer;
    }

}
