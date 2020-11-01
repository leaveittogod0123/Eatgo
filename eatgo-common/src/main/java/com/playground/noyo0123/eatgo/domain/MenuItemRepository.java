package com.playground.noyo0123.eatgo.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MenuItemRepository extends CrudRepository<MenuItem, Long> {
    List<MenuItem> findAllByRestaurantId(Long id);
    void deleteById(Long id);
}
