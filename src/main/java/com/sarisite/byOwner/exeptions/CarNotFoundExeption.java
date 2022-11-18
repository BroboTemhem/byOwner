package com.sarisite.byOwner.exeptions;

public class CarNotFoundExeption extends RuntimeException{
    public CarNotFoundExeption(String message) {
        super(message);
    }
}
