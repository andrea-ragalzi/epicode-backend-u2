package com.example.day1;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Pizza extends MenuItem {
    private List<MenuItem> toppings;
    private int calories;
    private boolean isLarge;

    public Pizza(String name, double price, int calories, boolean isLarge) {
        super(name, isLarge ? price + 4.15 : price);
        this.calories = isLarge ? calories * 2 : calories;
        this.isLarge = isLarge;
    }

    public Pizza(
            String name, double price, List<MenuItem> toppings, int calories,
            boolean isLarge) {
        super(name, isLarge ? price + 4.15 : price);
        this.calories = isLarge ? calories * 2 : calories;
        this.toppings = toppings;
        this.isLarge = isLarge;
    }

    @Override
    public String toString() {
        return "\n\tPizza{" +
                "\n\t\tname='" + this.name + '\'' +
                ",\n\t\tprice=" + this.price +
                ",\n\t\ttoppings=" + toppings +
                ",\n\t\tisLarge=" + isLarge +
                "\n\t}\n\t";
    }
}