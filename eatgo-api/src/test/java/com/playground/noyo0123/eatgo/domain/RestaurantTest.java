package com.playground.noyo0123.eatgo.domain;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class RestaurantTest {
    @Test
    public void create() {
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .build();

        assertThat(restaurant.getName(), is("Bob zip"));
        assertThat(restaurant.getAddress(), is("Seoul"));
    }

    @Test
    public void information() {
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("BobZip")
                .address("Seoul")
                .build();

        assertThat(restaurant.getInformation(), is("Bob zip in Seoul"));
    }
}