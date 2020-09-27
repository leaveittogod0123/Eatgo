package com.playground.noyo0123.eatgo.interfaces;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class) // Restaurant 클래스를 web mvc로 테스트한다.
public class RestaurantControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void list() throws Exception {
        mvc.perform(get("/restaurants")) // perform은 예외가 있음.
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("Bob zip")))
                .andExpect(content().string(
                        containsString("\"id\":1004")
                ));
    }

    @Test
    public void detail() throws Exception {
        mvc.perform(get("/restaurants/1004"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("Bob zip")))
                .andExpect(content().string(
                        containsString("\"id\":1004")
                ));

        mvc.perform(get("/restaurants/2020"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("Cyber food")))
                .andExpect(content().string(
                        containsString("\"id\":2020")
                ));

    }
}