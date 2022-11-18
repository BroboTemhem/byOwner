package com.sarisite.byOwner.exeptions;

public class BrandNotFoundExeption extends RuntimeException{
    public BrandNotFoundExeption(String message) {
        super(message);
    }
}
