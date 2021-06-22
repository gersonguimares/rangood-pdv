package br.com.rangood.pdv.rangoodpdvusermanagementservice.controller;

import br.com.rangood.pdv.rangoodpdvusermanagementservice.entity.Role;
import br.com.rangood.pdv.rangoodpdvusermanagementservice.entity.User;
import br.com.rangood.pdv.rangoodpdvusermanagementservice.service.RoleService;
import br.com.rangood.pdv.rangoodpdvusermanagementservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class AccountUser {

    final private UserService userService;
    final private RoleService roleService;
    final private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AccountUser(UserService userService, RoleService roleService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping
    public ResponseEntity registerUser(@RequestBody AddUserResquestModel addUserResquestModel) {

        Role role = roleService.add(addUserResquestModel.getRole());
        String password = bCryptPasswordEncoder.encode(addUserResquestModel.getPassword());

        User user = new User(
                addUserResquestModel.getName(),
                addUserResquestModel.getUsername(),
                password
        );
        user.addRole(role);
        userService.add(user);

        return ResponseEntity.status(HttpStatus.CREATED).build();

//        Role role = roleService.getByRoleName("OPERATOR");
//        String password = bCryptPasswordEncoder.encode(addUserResquestModel.getPassword());
//
//        User user = new User(
//                addUserResquestModel.getName(),
//                addUserResquestModel.getUsername(),
//                password
//        );
//        user.addRole(role);
//        userService.add(user);
//
//        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity registerInScriptUser(@RequestBody AddUserResquestModel addUserResquestModel) {

        Role role = roleService.add(addUserResquestModel.getRole());
        String password = bCryptPasswordEncoder.encode(addUserResquestModel.getPassword());

        User user = new User(
                addUserResquestModel.getName(),
                addUserResquestModel.getUsername(),
                password
        );
        user.addRole(role);
        userService.add(user);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
