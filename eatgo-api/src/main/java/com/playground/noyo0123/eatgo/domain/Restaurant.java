package com.playground.noyo0123.eatgo.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String address;

    @Transient
    private List<MenuItem> menuItems = new ArrayList<MenuItem>();

    public Restaurant(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Restaurant(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public String getInformation() {
        return this.name + " in " + this.address;
    }

    public List<MenuItem> getMenuItems (){
        return this.menuItems;
    }

    public void addMenuItem(MenuItem menuItem) {
        this.menuItems.add(menuItem);
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        for(MenuItem menuItem: menuItems){
            this.addMenuItem(menuItem);
        }
    }
}
