package com.sarisite.byOwner.exeptions;

public class ColorNotFoundExeption extends RuntimeException{
    public ColorNotFoundExeption(String message) {
        super(message);
    }
}
