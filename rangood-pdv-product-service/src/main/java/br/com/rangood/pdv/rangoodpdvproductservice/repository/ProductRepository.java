package br.com.rangood.pdv.rangoodpdvproductservice.repository;

import br.com.rangood.pdv.rangoodpdvproductservice.model.Product;
import br.com.rangood.pdv.rangoodpdvproductservice.model.ProductClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product,UUID> {

    Optional<Product> findByProductClass(ProductClass productClass);

}
