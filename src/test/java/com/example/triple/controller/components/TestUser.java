package com.example.triple.controller.components;

import com.example.triple.model.user.User;
import com.example.triple.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestUser{
    @Autowired
    private UserRepository userRepository;
    public User saveUser(String Uuid){
        return userRepository.save(new User(Uuid, 0));
    }
}
