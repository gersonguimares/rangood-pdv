package br.com.rangood.pdv.rangoodpdvproductservice.service;

import br.com.rangood.pdv.rangoodpdvproductservice.model.ProductClass;
import br.com.rangood.pdv.rangoodpdvproductservice.service.exceptions.EntityAlreadyExistException;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductClassServiceTest {

    @Autowired
    private ProductClassService productClassService;

    @Test
    public void shouldAddProductClass() throws EntityAlreadyExistException {

    final Random random = new Random();
    final String categoryRandomName = "CATEGORIA_"+ random.nextLong();
    ProductClass productClass = new ProductClass(categoryRandomName);
    productClass = productClassService.add(productClass);

    final ProductClass persistedProductClass = productClassService.findById(productClass.getId());
    assertEquals(persistedProductClass.getId(), productClass.getId());
    assertEquals(persistedProductClass.getName(), productClass.getName());

    }

    @Test(expected = EntityAlreadyExistException.class)
    public void shouldNotAddProductClass_whenEntityAlreadyExist() throws EntityAlreadyExistException {

        final Random random = new Random();
        final String categoryRandomName = "CATEGORIA_"+ random.nextLong();
        ProductClass productClass = new ProductClass(categoryRandomName);
        productClass = productClassService.add(productClass);

        ProductClass newProductClass = new ProductClass(categoryRandomName);
        productClass = productClassService.add(newProductClass);


    }
}
