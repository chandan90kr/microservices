package com.techotekkie.user.service.UserService.services.impl;

import com.techotekkie.user.service.UserService.entities.User;
import com.techotekkie.user.service.UserService.exceptions.ResourceNotFoundException;
import com.techotekkie.user.service.UserService.repositories.UserRepository;
import com.techotekkie.user.service.UserService.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserServices {

    @Autowired
    private UserRepository userRepository;
    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        return userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with given on id not found on server"+userId));
    }
}
