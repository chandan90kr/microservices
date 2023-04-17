package com.techotekkie.user.service.UserService.services;

import com.techotekkie.user.service.UserService.entities.User;

import java.util.List;

public interface UserServices {
    //user operation

    //create

    User saveUser(User user);


    //getalluser
    List<User> getAllUser();

    //get single user
    User getUser(String userId);

    //TODO: Delete
    //TODO: UPDATE
}
