package com.techotekkie.user.service.UserService.services.impl;

import com.techotekkie.user.service.UserService.entities.User;
import com.techotekkie.user.service.UserService.exceptions.ResourceNotFoundException;
import com.techotekkie.user.service.UserService.repositories.UserRepository;
import com.techotekkie.user.service.UserService.services.UserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserServices {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
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
        //get user from database with the help of repository
        User user =userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with given on id not found on server"+userId));

        //fetch rating of the above user from rating service

        ArrayList arrayList =restTemplate.getForObject("http://localhost:8083/ratings/users/"+user.getUserId(), ArrayList.class);
        logger.info("{}",arrayList);
        user.setRatings(arrayList);
        return user;
    }
}
