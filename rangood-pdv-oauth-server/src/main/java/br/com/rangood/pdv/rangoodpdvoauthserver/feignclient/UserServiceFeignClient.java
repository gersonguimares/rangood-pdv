package br.com.rangood.pdv.rangoodpdvoauthserver.feignclient;

import br.com.rangood.pdv.rangoodpdvoauthserver.entities.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "rangood-pdv-user-management", path = "/user")
public interface UserServiceFeignClient {

    @GetMapping("/search")
    ResponseEntity<User> findByUserName(@RequestParam String username);
}
