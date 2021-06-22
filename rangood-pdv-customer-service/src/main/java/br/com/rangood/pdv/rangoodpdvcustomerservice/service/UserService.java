package br.com.rangood.pdv.rangoodpdvcustomerservice.service;

import br.com.rangood.pdv.rangoodpdvcustomerservice.feignclient.userfeignservice.AddUserResquestModel;
import br.com.rangood.pdv.rangoodpdvcustomerservice.feignclient.userfeignservice.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserFeignClient userFeignClient;

    @Autowired
    public UserService(UserFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;
    }

    public boolean registerUserAccount(AddUserResquestModel addUserResquestModel) {
        ResponseEntity responseEntity = userFeignClient.registerUser(addUserResquestModel);
        if(responseEntity.getStatusCode() == HttpStatus.CREATED) {
            return true;
        } else {
            return false;
        }
    }

}
