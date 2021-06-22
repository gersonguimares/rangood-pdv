package br.com.rangood.pdv.rangoodpdvusermanagementservice.repository;

import br.com.rangood.pdv.rangoodpdvusermanagementservice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {

    Optional<Role> findById(UUID uuid);

    Optional<Role> findByRoleName(String roleName);
}
