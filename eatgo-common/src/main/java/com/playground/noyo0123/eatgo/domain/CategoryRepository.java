package com.playground.noyo0123.eatgo.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    public List<Category> findAll();

    public Category save(Category category);
}
