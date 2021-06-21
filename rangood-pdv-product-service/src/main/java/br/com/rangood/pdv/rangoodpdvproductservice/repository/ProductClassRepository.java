package br.com.rangood.pdv.rangoodpdvproductservice.repository;

import br.com.rangood.pdv.rangoodpdvproductservice.model.Product;
import br.com.rangood.pdv.rangoodpdvproductservice.model.ProductClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductClassRepository extends JpaRepository<ProductClass, UUID> {

    Optional<ProductClass> findByName(String name);

}
