package com.playground.noyo0123.eatgo.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class UserController {

    @PostMapping("/users")
    public ResponseEntity<?> create() throws URISyntaxException {

        String email = "tester@example.com";
        String name = "Tester";
        String password = "test";

        User user = User.builder()
                .email(email)
                .name(name)
                .password(password)
                .build();

        String url = "/users/1004";
        return ResponseEntity.created(new URI(url)).body("{}");
    }
}
