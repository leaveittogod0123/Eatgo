package com.playground.noyo0123.eatgo.interfaces;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    MockMvc mvc;


    @Test
    public void create() throws Exception {
      mvc.perform(post("/users")
              .contentType(MediaType.APPLICATION_JSON)
              .content("{\"name\": \"tester@example.com\", \"name\":\"Tester\", \"password\":\"test\"}"))
              .andExpect(status().isCreated())
              .andExpect(header().string("location", "/users/1004"));
    }
}