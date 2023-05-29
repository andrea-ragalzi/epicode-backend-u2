package com.example.day1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class GodfathersPizzaMenuConfiguration {

    @Bean
    public List<MenuItem> pizzas() {
        List<MenuItem> pizzas = new ArrayList<>();
        pizzas.add(new Pizza(
                "Cheese",
                6.49,
                toppings(),
                92,
                true));
        pizzas.add(new Pizza("Ham", 6.49, 35, false));
        return pizzas;
    }

    @Bean
    public List<MenuItem> toppings() {
        List<MenuItem> toppings = new ArrayList<>();
        toppings.add(new Topping("Cheese", 0.69, 1));
        toppings.add(new Topping("Ham", 0.99, 2));
        toppings.add(new Topping("Pineapple", 0.69, 3));
        return toppings;
    }

    @Bean
    public List<MenuItem> drinks() {
        List<MenuItem> drinks = new ArrayList<>();
        drinks.add(new Drink("Lemonade", 1.29, 0));
        drinks.add(new Drink("Water", 1.29, 0));
        return drinks;
    }

    @Bean
    public List<MenuItem> franchiseItems() {
        List<MenuItem> franchiseItems = new ArrayList<>();
        franchiseItems.add(new FranchiseItem("Shirt", 21.99));
        franchiseItems.add(new FranchiseItem("Mug", 4.99));
        return franchiseItems;
    }

}