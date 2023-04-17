package com.techotekkie.user.service.UserService.controller;
import com.techotekkie.user.service.UserService.entities.User;
import com.techotekkie.user.service.UserService.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//TODO: This class is used handle the controller class where we write our logic to implement or start
@RestController
@RequestMapping("/users")

public class UserController {
    //ceate

    @Autowired
    private UserServices userServices;
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user)
    {
        User user1 = userServices.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    @GetMapping("/{userId}")
    public  ResponseEntity<User> getSingleUser(@PathVariable String userId){

        User user= userServices.getUser(userId);
        return ResponseEntity.ok(user);

    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        List<User> allUser =userServices.getAllUser();
        return ResponseEntity.ok(allUser);
    }
}
