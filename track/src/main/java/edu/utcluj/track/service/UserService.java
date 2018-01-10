package edu.utcluj.track.service;

import edu.utcluj.track.model.User;
import edu.utcluj.track.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }
}
