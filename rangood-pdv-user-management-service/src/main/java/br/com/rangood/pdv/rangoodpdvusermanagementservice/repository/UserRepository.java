package br.com.rangood.pdv.rangoodpdvusermanagementservice.repository;

import br.com.rangood.pdv.rangoodpdvusermanagementservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    User findByUsername(String username);

}
