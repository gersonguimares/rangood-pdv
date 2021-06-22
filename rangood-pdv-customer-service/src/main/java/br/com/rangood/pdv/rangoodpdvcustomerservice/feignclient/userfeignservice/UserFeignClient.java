package br.com.rangood.pdv.rangoodpdvcustomerservice.feignclient.userfeignservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Component
@FeignClient(name = "rangood-pdv-user-management", path = "/user")
public interface UserFeignClient {

    @PostMapping
    public ResponseEntity registerUser(@RequestBody AddUserResquestModel addUserResquestModel);

}
