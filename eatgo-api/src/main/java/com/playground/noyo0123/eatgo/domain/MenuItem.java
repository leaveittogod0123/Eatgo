package com.playground.noyo0123.eatgo.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
public class MenuItem {

    @Id
    private Long id;

    private Long restaurantId;

    private String name;

    public MenuItem(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
