package br.com.rangood.pdv.rangoodpdvusermanagementservice.service;

import br.com.rangood.pdv.rangoodpdvusermanagementservice.entity.Role;
import br.com.rangood.pdv.rangoodpdvusermanagementservice.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role add(String roleName) {

        Role role = new Role(roleName.toUpperCase(Locale.ROOT));
        return roleRepository.save(role);

    }

    public Role getByRoleName(String roleNome) {
        final Optional<Role> role = roleRepository.findByRoleName(roleNome);
        if(role.isEmpty()) {
            return null;
        } else {
            return  role.get();
        }
    }


}
