package com.example.day1;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Topping extends MenuItem {
    private int amount;

    public Topping(String name, double price, int amount) {
        super(name, price);
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "\n\tTopping{" +
                "\n\t\tname='" + this.name + '\'' +
                ",\n\t\tprice=" + this.price +
                ",\n\t\tamount=" + this.amount +
                "\n\t}\n\t";
    }
}
