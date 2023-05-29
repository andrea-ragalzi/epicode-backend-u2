package com.example.day1;

import java.util.List;

import com.example.day1.interfaces.IMenu;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GodfathersPizzaMenu implements IMenu {
    private List<MenuItem> pizzas;
    private List<MenuItem> toppings;
    private List<MenuItem> drinks;
    private List<MenuItem> franchiseItems;

    public String toString() {
        return "GodfathersPizzaMenu{" +
                "\n\tpizzas=\n\t" + pizzas +
                ",\n\ttoppings=\n\t" + toppings +
                ",\n\tdrinks=\n\t" + drinks +
                ",\n\tfranchiseItems=\n\t" + franchiseItems +
                "\n}";
    }

}