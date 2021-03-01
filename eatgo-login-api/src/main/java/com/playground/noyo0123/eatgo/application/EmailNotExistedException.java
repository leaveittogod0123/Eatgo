package com.playground.noyo0123.eatgo.application;

public class EmailNotExistedException extends RuntimeException {

    EmailNotExistedException(String email) {
        super("Email is not existed" + email);
    }
}
