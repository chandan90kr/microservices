package com.techotekkie.user.service.UserService.services.impl;

import com.techotekkie.user.service.UserService.entities.Hotel;
import com.techotekkie.user.service.UserService.entities.Rating;
import com.techotekkie.user.service.UserService.entities.User;
import com.techotekkie.user.service.UserService.exceptions.ResourceNotFoundException;
import com.techotekkie.user.service.UserService.repositories.UserRepository;
import com.techotekkie.user.service.UserService.services.UserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

        Rating[] arrayList =restTemplate.getForObject("http://localhost:8083/ratings/users/"+user.getUserId(), Rating[].class);
        logger.info("{}",arrayList);
        List<Rating> ratings = Arrays.stream(arrayList).toList();

        List<Rating> ratingList = ratings.stream().map(rating -> {
            //api call to hotel service to get the hotel
            //http://localhost:8082/hotels/cf3cbe42-99be-4016-abf2-4bfdb4dea460
            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://localhost:8082/hotels/"+rating.getHotelId(),Hotel.class);
            //set the hotel to rating
            Hotel hotel = forEntity.getBody();
            rating.setHotel(hotel);
            logger.info("response status code:"+forEntity.getStatusCode());
            //return the rating
            return rating;

        }).collect(Collectors.toList());
        user.setRatings(ratingList);
        return user;
    }
}
