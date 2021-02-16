package com.playground.noyo0123.eatgo.application;

public class PasswordWrongException extends RuntimeException {

    PasswordWrongException() {
        super("Password is wrong");
    }
}
