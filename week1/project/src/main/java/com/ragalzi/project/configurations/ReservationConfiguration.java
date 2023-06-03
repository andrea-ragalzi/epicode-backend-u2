package com.ragalzi.project.configurations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.github.javafaker.Faker;
import com.ragalzi.project.models.Reservation;

@Configuration
public class ReservationConfiguration {
    @Bean("ReservationNoParamsBean")
    @Scope("prototype")
    Reservation reservationNoParams() {
        return new Reservation();
    }

    @Bean("ReservationWithParamsBean")
    @Scope("prototype")
    Reservation reservationWithParams(
            LocalDate date, LocalTime startTime, LocalTime endTime) {
        return new Reservation();
    }

    @Bean("ReservationFakeBean")
    @Scope("prototype")
    Reservation reservationFake() {
        Faker faker = new Faker(new Locale("it"));
        LocalDate date = LocalDate.now().plusMonths(faker.number().numberBetween(-12, 12));
        LocalTime startTime = LocalTime.of(faker.number().numberBetween(9, 17), 0);
        LocalTime endTime = startTime.plusHours(faker.number().numberBetween(1, 9))
                .withMinute(0);
        endTime = endTime.isAfter(LocalTime.of(18, 0)) ? LocalTime.of(18, 0) : endTime;
        return new Reservation(date, startTime, endTime);
    }

}
