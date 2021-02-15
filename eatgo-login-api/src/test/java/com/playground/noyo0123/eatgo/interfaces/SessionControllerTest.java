package com.playground.noyo0123.eatgo.interfaces;

import com.playground.noyo0123.eatgo.application.EmailNotExistedException;
import com.playground.noyo0123.eatgo.application.PasswordWrongException;
import com.playground.noyo0123.eatgo.application.UserService;
import com.playground.noyo0123.eatgo.domain.User;
import com.playground.noyo0123.eatgo.utils.JwtUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(SessionController.class)
public class SessionControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    private UserService userService;

    @MockBean
    private JwtUtil jwtUtil;


    @Test
    public void createWithValidAttributes() throws Exception {
        Long id = 1004L;
        String name = "tester";
        String email = "tester@example.com";
        String password = "test";


        User mockUser = User.builder().name(name).id(id).build();

        BDDMockito.given(userService.authenticate(email, password))
                .willReturn(mockUser);

        BDDMockito.given(jwtUtil.createToken(id, name))
                .willReturn("header.payload.signature");

        mvc.perform(MockMvcRequestBuilders.post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\": \"tester@example.com\", \"password\": \"test\"}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().string("location", "/session"))
                .andExpect(MockMvcResultMatchers.content().string(StringContains.containsString("{\"accessToken\":\"header.payload.signature\"}")));

        Mockito.verify(userService).authenticate(ArgumentMatchers.eq(email), ArgumentMatchers.eq(password));
    }

    @Test
    public void createWithInValidAttributes() throws Exception {

        BDDMockito.given(userService.authenticate("tester@example.com", "x"))
                .willThrow(PasswordWrongException.class);

        mvc.perform(MockMvcRequestBuilders.post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\": \"tester@example.com\", \"password\": \"x\"}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        Mockito.verify(userService).authenticate(ArgumentMatchers.eq("tester@example.com"), ArgumentMatchers.eq("x"));
    }

    @Test
    public void createWithNotExistedEmail() throws Exception {

        BDDMockito.given(userService.authenticate("x@example.com", "test"))
                .willThrow(EmailNotExistedException.class);

        mvc.perform(MockMvcRequestBuilders.post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\": \"x@example.com\", \"password\": \"test\"}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        Mockito.verify(userService).authenticate(ArgumentMatchers.eq("x@example.com"), ArgumentMatchers.eq("test"));
    }

    @Test
    public void createWithWorngPassword() throws Exception {

        BDDMockito.given(userService.authenticate("tester@example.com", "x"))
                .willThrow(PasswordWrongException.class);

        mvc.perform(MockMvcRequestBuilders.post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\": \"tester@example.com\", \"password\": \"x\"}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        Mockito.verify(userService).authenticate(ArgumentMatchers.eq("tester@example.com"), ArgumentMatchers.eq("x"));
    }

}