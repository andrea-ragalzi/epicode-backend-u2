package com.ragalzi.project.configurations;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.github.javafaker.Faker;
import com.ragalzi.project.models.Building;

@Configuration
public class BuildingConfiguration {

    @Bean("BuildingNoParamsBean")
    @Scope("prototype")
    Building buildingNoParams() {
        return new Building();
    }

    @Bean("BuildingWithParamsBean")
    @Scope("prototype")
    Building buildingWithParams(String name, String address, String city) {
        return new Building();
    }

    @Bean("BuildingFakeBean")
    @Scope("prototype")
    Building buildingFake() {
        Faker faker = new Faker(new Locale("it"));
        String name = faker.company().name();
        String address = faker.address().streetAddress();
        String city = faker.address().city();
        return new Building(name, address, city);
    }

}
