package br.com.rangood.pdv.rangoodpdvoauthserver.service;

import br.com.rangood.pdv.rangoodpdvoauthserver.entities.User;
import br.com.rangood.pdv.rangoodpdvoauthserver.feignclient.UserServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    final private UserServiceFeignClient userServiceFeignClient;

    @Autowired
    public UserService(UserServiceFeignClient userServiceFeignClient) {
        this.userServiceFeignClient = userServiceFeignClient;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userServiceFeignClient.findByUserName(username).getBody();
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}
