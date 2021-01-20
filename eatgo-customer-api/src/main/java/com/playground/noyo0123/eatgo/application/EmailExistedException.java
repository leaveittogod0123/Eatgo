package com.playground.noyo0123.eatgo.application;

public class EmailExistedException extends RuntimeException {
    public EmailExistedException(String email) {
        super("Email is already registered: " + email);
    }
}
