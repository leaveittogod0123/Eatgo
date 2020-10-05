package com.playground.noyo0123.eatgo.interfaces;

import com.playground.noyo0123.eatgo.application.RestaurantService;
import com.playground.noyo0123.eatgo.domain.MenuItem;
import com.playground.noyo0123.eatgo.domain.MenuItemRepository;
import com.playground.noyo0123.eatgo.domain.Restaurant;
import com.playground.noyo0123.eatgo.domain.RestaurantRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class) // Restaurant 클래스를 web mvc로 테스트한다.
public class RestaurantControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean(RestaurantService.class)
    private RestaurantService restaurantService;

//    @SpyBean(RestaurantRepositoryImpl.class) // 컨트롤러에 객체 주입이 가능함 (어떤 구현체를 사용할 것인지)?
//    private RestaurantRepository restaurantRepository;
//
//    @SpyBean(MenuItemRepositoryImpl.class)
//    private MenuItemRepository menuItemRepository;

    @Test
    public void list() throws Exception {

        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant(1004L, "Bob zip", "Seoul"));
        given(restaurantService.getRestaurants()).willReturn(restaurants);

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

        Restaurant restaurant = new Restaurant(1004L, "JOKER House", "Seoul");
        restaurant.addMenuItem(new MenuItem("Kimchi"));
        given(restaurantService.getRestaurant(1004L)).willReturn(restaurant);
        Restaurant restaurant2 = new Restaurant(2020L, "Cyber food", "Busan");
        given(restaurantService.getRestaurant(2020L)).willReturn(restaurant2);

        mvc.perform(get("/restaurants/1004"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("JOKER House")))
                .andExpect(content().string(
                        containsString("\"id\":1004")
                ))
                .andExpect(content().string(
                        containsString("Kimchi")
                ));

        mvc.perform(get("/restaurants/2020"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("Cyber food")))
                .andExpect(content().string(
                        containsString("\"id\":2020")
                ));

    }

    @Test
    public void update() throws Exception {
        mvc.perform(patch("/restaruants/1004")
            .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"JOKER BAR\", \"address\": \"Busan\"}"))
                .andExpect(status().isOk());

    }
}