package com.suelen.learningtestingjava.exceptions;

public class DataIntegrityViolation extends RuntimeException{
    public DataIntegrityViolation(String message) {
        super(message);
    }
}
