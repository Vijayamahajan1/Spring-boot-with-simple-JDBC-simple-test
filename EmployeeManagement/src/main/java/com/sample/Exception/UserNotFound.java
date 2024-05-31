package com.sample.Exception;

public class UserNotFound extends RuntimeException {
    public UserNotFound(String msg){
        super(msg);
    }
    
}
