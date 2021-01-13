package com.playground.noyo0123.eatgo.application;

import com.playground.noyo0123.eatgo.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

public class RestaurantServiceTest {

    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);

        mockRestaurantRepository();

        //의존관게 직접 맺어주기
        restaurantService = new RestaurantService(restaurantRepository);
    }

    private void mockRestaurantRepository() {
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = Restaurant.builder()
            .id(1004L)
            .categoryId(1L)
            .name("Bob zip")
            .address("Seoul")
            .build();

        restaurants.add(restaurant);
        given(restaurantRepository.findAll())
                .willReturn(restaurants);

        given(restaurantRepository.findById(1004L))
                .willReturn(Optional.of(restaurant));


    }

    @Test
    public void getRestaurants() {
        List<Restaurant> restaurants = restaurantService.getRestaurants();

        Restaurant restaurant = restaurants.get(0);

        assertThat(restaurant.getId(), is(1004L));

    }

    @Test
    public void getRestaurantWithExisted() {
        Restaurant restaurant = restaurantService.getRestaurant(1004L);

        assertThat(restaurant.getId(), is(1004L));

    }

//    @Test(expected = RestaurantNotFoundException.class)
//    public void getRestaurantWithNotExisted() {
////        restaurantService.getRestaurant(1004L);
//
//        assertThatThrownBy(() -> {
//            restaurantService.getRestaurant(404L);
//        }).isInstanceOf(RestaurantNotFoundException.class);
//    }

    @Test
    public void getRestaurantWithNotExisted() {
        assertThatThrownBy(() -> {
            restaurantService.getRestaurant(404L);
        }).isInstanceOf(RestaurantNotFoundException.class);
    }

    @Test
    public void addRestaurant() {

        given(restaurantRepository.save(any())).will(invocation -> {
            Restaurant restaurant = invocation.getArgument(0);
            restaurant.setId(1234L);
            return restaurant;
        });



        Restaurant restaurant = Restaurant.builder()
            .name("BeRyong")
            .address("Busan").build();
        Restaurant saved = Restaurant.builder()
            .id(1234L)
            .name("BeRyong")
            .address("Busan")
            .build();
        Restaurant created = restaurantService.addRestaurant(restaurant);
        assertThat(created.getId(), is(1234L));
    }

    @Test
    public void updateRestaurant() {

        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("Bob Zip")
                .address("Seoul")
                .build();
        given(restaurantRepository.findById(1004l)).willReturn(Optional.of(restaurant));

        restaurantService.updateRestaurant(1004L, "Sool zip", "Busan");
        assertThat(restaurant.getName(), is("Sool zip"));
        assertThat(restaurant.getAddress(), is("Busan"));
    }
}