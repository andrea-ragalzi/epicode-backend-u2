package com.example.day1.interfaces;

import java.util.List;

import com.example.day1.MenuItem;

public interface IMenu {
    List<MenuItem> getPizzas();

    List<MenuItem> getToppings();

    List<MenuItem> getDrinks();

    List<MenuItem> getFranchiseItems();
}