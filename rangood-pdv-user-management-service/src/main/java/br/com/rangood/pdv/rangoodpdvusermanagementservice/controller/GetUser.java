package br.com.rangood.pdv.rangoodpdvusermanagementservice.controller;

import br.com.rangood.pdv.rangoodpdvusermanagementservice.entity.User;
import br.com.rangood.pdv.rangoodpdvusermanagementservice.exception.UserNotFoundException;
import br.com.rangood.pdv.rangoodpdvusermanagementservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class GetUser {

    private final UserService userService;

    @Autowired
    public GetUser(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/search")
    public ResponseEntity<User> findByUserName(@RequestParam String username) {

        try {
            User user = userService.findByUsername(username);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
