package com.example.day1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Drink extends MenuItem {
    private int calories;

    public Drink(String name, double price, int calories) {
        super(name, price);
        this.calories = calories;
    }

    @Override
    public String toString() {
        return "\n\tDrink{" +
                "\n\t\tname='" + this.name + '\'' +
                ",\n\t\tprice=" + this.price +
                ",\n\t\tcalories=" + this.calories +
                "\n\t}\n\t";
    }
}