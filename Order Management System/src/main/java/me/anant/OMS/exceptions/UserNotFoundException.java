package me.anant.OMS.exceptions;

public class UserNotFoundException extends Exception {

    private String errorMessage;

    public UserNotFoundException(String message){
        super(message);
        this.errorMessage = message;
    }

}
