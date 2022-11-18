package com.sarisite.byOwner.exeptions;

public class ModelNotFoundExeption extends RuntimeException{
    public ModelNotFoundExeption(String message) {
        super(message);
    }
}
