package com.playground.noyo0123.eatgo.domain;

import com.playground.noyo0123.eatgo.domain.Restaurant;
import org.springframework.stereotype.Component;

import java.util.List;

public interface MenuItemRepository {

    List<MenuItem> findAllByRestaurantId(Long id);
}
