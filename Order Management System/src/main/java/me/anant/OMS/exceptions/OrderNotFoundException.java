package me.anant.OMS.exceptions;

public class OrderNotFoundException extends Exception{

    private String errorMessage;
    public OrderNotFoundException(String message){
        super(message);
        this.errorMessage=message;
    }
}
