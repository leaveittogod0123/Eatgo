package com.playground.noyo0123.eatgo.interfaces;

import com.playground.noyo0123.eatgo.application.RestaurantService;
import com.playground.noyo0123.eatgo.domain.MenuItem;
import com.playground.noyo0123.eatgo.domain.MenuItemRepository;
import com.playground.noyo0123.eatgo.domain.Restaurant;
import com.playground.noyo0123.eatgo.domain.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public List<Restaurant> list() {
        List<Restaurant> restaurants = restaurantService.getRestaurants();
        return restaurants;
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable Long id) {

        return restaurantService.getRestaurant(id);
    }

//    @PostMapping("/restaurants")
//    public Res
}
