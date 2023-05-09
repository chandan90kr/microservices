package com.techotekkie.user.service.UserService.controller;
import com.techotekkie.user.service.UserService.entities.User;
import com.techotekkie.user.service.UserService.services.UserServices;
import com.techotekkie.user.service.UserService.services.impl.UserServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


//TODO: This class is used handle the controller class where we write our logic to implement or start
@RestController
@RequestMapping("/users")

public class UserController {
    //ceate

    @Autowired
    private UserServices userServices;

    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user)
    {
        User user1 = userServices.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    int retryCount= 1;
    @GetMapping("/{userId}")
   // @CircuitBreaker(name = "ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
    @Retry(name ="ratingHotelService", fallbackMethod = "ratingHotelFallback")

    public  ResponseEntity<User> getSingleUser(@PathVariable String userId){

        logger.info("Retry Count: {}",retryCount);
        retryCount++;
        User user= userServices.getUser(userId);
        return ResponseEntity.ok(user);

    }
    //creating fallback method for circuitbreaker
    public ResponseEntity<User> ratingHotelFallback(String userId,Exception ex){
        logger.info("Fallback is executed because service is dow : ",ex.getMessage());
        User user = User.builder()
                    .email("dummy@gmail.com")
                    .name("Dummy")
                    .about("This user is created dummy because some service is down")
                    .userId("11231")
                    .build();
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        List<User> allUser =userServices.getAllUser();
        return ResponseEntity.ok(allUser);
    }
}
