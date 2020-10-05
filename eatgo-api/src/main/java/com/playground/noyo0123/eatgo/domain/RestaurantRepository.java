package com.playground.noyo0123.eatgo.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    List<Restaurant> findAll();

    Restaurant save(Restaurant restaurant);

    Optional<Restaurant> findById(Long id);
}
