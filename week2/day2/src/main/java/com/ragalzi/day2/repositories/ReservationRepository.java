package com.ragalzi.day2.repositories;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ragalzi.day2.models.Reservation;
import com.ragalzi.day2.models.User;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByDateAndStartTimeBetween(
            LocalDate date, LocalTime startTime, LocalTime endTime);

    List<Reservation> findByUserAndDate(User user, LocalDate date);
}