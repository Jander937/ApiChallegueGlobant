package com.marketplace.marketplace.exception;

public class ProductExistException extends RuntimeException{
    public ProductExistException(String message){
        super(message);
    }
}
