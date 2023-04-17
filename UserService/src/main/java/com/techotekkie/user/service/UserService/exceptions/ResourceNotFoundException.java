package com.techotekkie.user.service.UserService.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    //TODO: This class is used handle the exception class when we impl logic to get data or send data
    public ResourceNotFoundException(){
        super("resource not found on server!!");
    }

    public ResourceNotFoundException(String message){
        super(message);
    }
}
