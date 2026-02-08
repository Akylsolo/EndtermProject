package com.example.endterm.patterns;

import com.example.endterm.exception.BadRequestException;
import com.example.endterm.model.DrinkItem;
import com.example.endterm.model.FoodItem;
import com.example.endterm.model.MenuItem;

public class MenuItemFactory {
    public static MenuItem create(String type, String name, double price) {
        if (type == null) throw new BadRequestException("Invalid type");
        return switch (type.toUpperCase()) {
            case "FOOD" -> new FoodItem(name, price);
            case "DRINK" -> new DrinkItem(name, price);
            default -> throw new BadRequestException("Invalid type");
        };
    }
}
