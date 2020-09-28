package com.playground.noyo0123.eatgo.domain;

import java.util.ArrayList;
import java.util.List;


public class Restaurant {
    private final Long id;
    private final String name;
    private final String address;
    private List<MenuItem> menuItems = new ArrayList<MenuItem>();

    public Restaurant(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
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
