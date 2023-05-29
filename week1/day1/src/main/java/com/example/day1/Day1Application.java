package com.example.day1;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Day1Application {

	public static void main(String[] args) {
		SpringApplication.run(Day1Application.class, args);
		configWithBean();
	}

	public static void configWithBean() {
		AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(
				GodfathersPizzaMenuConfiguration.class);
		List<MenuItem> pizzas = (List<MenuItem>) appContext.getBean("pizzas");
		List<MenuItem> drinks = (List<MenuItem>) appContext.getBean("drinks");
		List<MenuItem> franchiseItems = (List<MenuItem>) appContext.getBean("franchiseItems");
		List<MenuItem> toppings = (List<MenuItem>) appContext.getBean("toppings");
		GodfathersPizzaMenu menu = new GodfathersPizzaMenu(pizzas, toppings, drinks, franchiseItems);
		System.out.println(menu);
		appContext.close();
	}

}
