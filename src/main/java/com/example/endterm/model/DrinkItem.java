package com.example.endterm.model;

public class DrinkItem extends MenuItem {
    public DrinkItem(String name, double price) {
        super(name, price);
    }

    public String getType() {
        return "DRINK";
    }
}
