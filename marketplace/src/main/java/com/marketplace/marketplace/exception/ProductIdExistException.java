package com.marketplace.marketplace.exception;

public class ProductIdExistException extends  RuntimeException{
    public ProductIdExistException(String message){
        super(message);
    }
}
