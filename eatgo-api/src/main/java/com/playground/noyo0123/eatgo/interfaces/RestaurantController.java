package com.playground.noyo0123.eatgo.interfaces;

import com.playground.noyo0123.eatgo.application.RestaurantService;
import com.playground.noyo0123.eatgo.domain.MenuItem;
import com.playground.noyo0123.eatgo.domain.MenuItemRepository;
import com.playground.noyo0123.eatgo.domain.Restaurant;
import com.playground.noyo0123.eatgo.domain.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.net.URISyntaxException;
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

    @PostMapping("/restaurants")
    public ResponseEntity<?> create(@RequestBody Restaurant resource ) throws URISyntaxException { // JSON으로 응답할거기때문에 ResponseEntity

        String name = resource.getName();
        String address = resource.getAddress();
        Restaurant restaurant = new Restaurant(name, address);
        restaurantService.addRestaurant(restaurant);
        
        
        URI uri = new URI("/restaurants/" + restaurant.getId());
        return ResponseEntity.created(uri).body("{}"); // JSON으로 넘겨줌
    }
}
