package com.playground.noyo0123.eatgo.interfaces;

import com.playground.noyo0123.eatgo.application.RestaurantService;
import com.playground.noyo0123.eatgo.domain.MenuItem;
import com.playground.noyo0123.eatgo.domain.Restaurant;
import com.playground.noyo0123.eatgo.domain.RestaurantNotFoundException;
import com.playground.noyo0123.eatgo.domain.Review;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class) // Restaurant 클래스를 web mvc로 테스트한다.
public class RestaurantControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean(RestaurantService.class)
    private RestaurantService restaurantService;

    @Test
    public void list() throws Exception {
        List<Restaurant> restaurants = new ArrayList<Restaurant>();
        restaurants.add(Restaurant.builder()
                .id(1004L)
                .categoryId(1L)
                .name("JOKER House")
                .address("Seoul")
                .build());

        given(restaurantService.getRestaurants("Seoul", 1L)).willReturn(restaurants);

        mvc.perform(get("/restaurants?region=Seoul&category=1")) // perform은 예외가 있음.
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"name\":\"JOKER House\"")))
                .andExpect(content().string(
                        containsString("\"id\":1004")
                ));
    }

    @Test
    public void detailWithExisted() throws Exception {

        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("JOKER House")
                .address("Seoul")
                .build();

        MenuItem menuItem = MenuItem.builder()
                .name("Kimchi")
                .build();
        restaurant.setMenuItems(Arrays.asList(menuItem));
        Review review = Review.builder()
                .name("JOKER")
                .score(5)
                .description("Great!")
                .build();

        restaurant.setReviews(Arrays.asList(review));

        given(restaurantService.getRestaurant(1004L)).willReturn(restaurant);

        mvc.perform(get("/restaurants/1004"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("JOKER House")))
                .andExpect(content().string(
                        containsString("\"id\":1004")
                ))
                .andExpect(content().string(
                        containsString("Kimchi")
                ))
                .andExpect(content().string(
                        containsString("Great!")
                ));

    }

    @Test
    public void detailWithNotExisted() throws Exception {

        given(restaurantService.getRestaurant(404L))
                .willThrow(new RestaurantNotFoundException(404L));

        mvc.perform(get("/restaurants/404"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("{}"));
    }
}