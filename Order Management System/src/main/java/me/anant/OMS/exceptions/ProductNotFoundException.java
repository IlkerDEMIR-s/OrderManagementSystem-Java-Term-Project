package me.anant.OMS.exceptions;

public class ProductNotFoundException extends Exception {

    private String errorMessage;

    public ProductNotFoundException(String message){
        super(message);
        this.errorMessage = message;
    }

}
