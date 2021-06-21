package br.com.rangood.pdv.rangoodpdvproductservice.service;

import br.com.rangood.pdv.rangoodpdvproductservice.model.Product;
import br.com.rangood.pdv.rangoodpdvproductservice.model.ProductClass;
import br.com.rangood.pdv.rangoodpdvproductservice.repository.ProductRepository;
import br.com.rangood.pdv.rangoodpdvproductservice.service.exceptions.EntityAlreadyExistException;
import br.com.rangood.pdv.rangoodpdvproductservice.service.exceptions.NotFoundEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(final Product product) {
        return productRepository.save(product);
    }

    public List<Product> getlAll() {
        return productRepository.findAll();
    }

    public Product findById(UUID productId) {
        final Optional<Product> optionalProduct = productRepository.findById(productId);
        if(optionalProduct.isPresent()) {
            return optionalProduct.get();
        } else {
            return null;
        }
    }

    public boolean isExist(UUID productId) {
        final Optional<Product> optionalProduct = productRepository.findById(productId);
        return optionalProduct.isPresent();
    }

    public boolean deleteById(UUID productId) throws NotFoundEntityException {

        if(!this.isExist(productId)) {
            throw new NotFoundEntityException();
        }

        try {
            productRepository.deleteById(productId);
            return true;
        } catch (DataAccessException ex) {
            return false;
        }
    }

    public Product update(Product product) throws NotFoundEntityException, EntityAlreadyExistException {

        if(this.isExist(product.getId())) {
            try {
                return productRepository.save(product);
            } catch (DataIntegrityViolationException e) { throw new EntityAlreadyExistException(e);}
        } else {
            throw new NotFoundEntityException();
        }
    }

}
