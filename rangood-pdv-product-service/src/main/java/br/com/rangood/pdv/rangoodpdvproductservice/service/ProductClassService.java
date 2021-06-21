package br.com.rangood.pdv.rangoodpdvproductservice.service;

import br.com.rangood.pdv.rangoodpdvproductservice.model.ProductClass;
import br.com.rangood.pdv.rangoodpdvproductservice.repository.ProductClassRepository;
import br.com.rangood.pdv.rangoodpdvproductservice.service.exceptions.EntityAlreadyExistException;
import br.com.rangood.pdv.rangoodpdvproductservice.service.exceptions.NotFoundEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductClassService {

    private final ProductClassRepository productClassRepository;

    @Autowired
    public ProductClassService(ProductClassRepository productClassRepository) {
        this.productClassRepository = productClassRepository;
    }

    public ProductClass add(final ProductClass productClass) throws EntityAlreadyExistException {

        //Change characters to uppercase
        productClass.setName(productClass.getName().toUpperCase(Locale.ROOT));
        try {
            productClassRepository.save(productClass);
        } catch (DataIntegrityViolationException e) { throw new EntityAlreadyExistException(e);}
        return productClass;
    }


    public List<ProductClass> getlAll() {
        return productClassRepository.findAll();
    }

    public boolean isExist(UUID productClassId) {
       final Optional<ProductClass> optionalProductClass = productClassRepository.findById(productClassId);
       return optionalProductClass.isPresent();
    }

    public boolean isExistByName(String productClassName) {
        final Optional<ProductClass> optionalProductClass = productClassRepository.findByName(productClassName);
        return optionalProductClass.isPresent();
    }

    public ProductClass findById(UUID productClassId) {
        final Optional<ProductClass> optionalProductClass = productClassRepository.findById(productClassId);
        if(optionalProductClass.isPresent()) {
            return optionalProductClass.get();
        } else {
            return null;
        }
    }

    public ProductClass findByName(String name) {
        final Optional<ProductClass> optionalProductClass = productClassRepository.findByName(name);
        if(optionalProductClass.isPresent()) {
            return optionalProductClass.get();
        } else {
            return null;
        }
    }

    public boolean deleteById(UUID productClassId) throws NotFoundEntityException {

        if(!this.isExist(productClassId)) {
            throw new NotFoundEntityException();
        }

        try {
            productClassRepository.deleteById(productClassId);
            return true;
        } catch (DataAccessException ex) {
            return false;
        }
    }

    public ProductClass update(ProductClass productClass) throws NotFoundEntityException, EntityAlreadyExistException {

         if(this.isExist(productClass.getId())) {
             try {
                 return productClassRepository.save(productClass);
             } catch (DataIntegrityViolationException e) { throw new EntityAlreadyExistException(e);}
         } else {
            throw new NotFoundEntityException();
         }
    }

}
