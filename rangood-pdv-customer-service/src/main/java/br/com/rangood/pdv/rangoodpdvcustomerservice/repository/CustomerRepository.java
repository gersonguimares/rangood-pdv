package br.com.rangood.pdv.rangoodpdvcustomerservice.repository;

import br.com.rangood.pdv.rangoodpdvcustomerservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,String> {

    Optional<Customer> findById(String id);

    Optional<Customer> findByUuid(UUID uuid);

}
