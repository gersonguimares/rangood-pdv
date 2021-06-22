package br.com.rangood.pdv.rangoodpdvusermanagementservice.service;

import br.com.rangood.pdv.rangoodpdvusermanagementservice.entity.User;
import br.com.rangood.pdv.rangoodpdvusermanagementservice.exception.UserNotFoundException;
import br.com.rangood.pdv.rangoodpdvusermanagementservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUsername(String email) throws UserNotFoundException{

        User user = userRepository.findByUsername(email);
        if (user == null) {
            throw new UserNotFoundException();
        }

        return user;
    }

    public User add(User user) {
        return userRepository.save(user);
    }

}
