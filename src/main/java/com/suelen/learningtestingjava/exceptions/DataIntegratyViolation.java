package com.suelen.learningtestingjava.exceptions;

public class DataIntegratyViolation extends RuntimeException{
    public DataIntegratyViolation(String message) {
        super(message);
    }
}
