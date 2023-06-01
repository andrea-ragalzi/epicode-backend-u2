package com.ragalzi.project.runners;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;
import com.ragalzi.project.models.Reservation;
import com.ragalzi.project.models.User;
import com.ragalzi.project.models.Workspace;
import com.ragalzi.project.repositories.ReservationRepository;
import com.ragalzi.project.repositories.UserRepository;
import com.ragalzi.project.repositories.WorkspaceRepository;

@Component
@Order(4)
public class ReservationRunner implements CommandLineRunner {
    @Autowired
    private WorkspaceRepository workspaceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    public static final int QUANTITY = 20;

    @Override
    public void run(String... args) throws Exception {
        List<Workspace> workspaces = workspaceRepository.findAll();
        List<User> users = userRepository.findAll();
        Faker faker = new Faker(new Locale("it"));
        Random rand = new Random();

        LocalDate startDate = LocalDate.now();
        LocalTime startTime = LocalTime.of(9, 0);

        for (int i = 0; i < ReservationRunner.QUANTITY; i++) {
            Workspace workspace = workspaces.get(rand.nextInt(workspaces.size()));
            User user = users.get(rand.nextInt(users.size()));
            LocalTime endTime = startTime.plusHours(
                    faker.number().numberBetween(1, 5));
            Reservation reservation = new Reservation(
                    null, startDate, startTime, endTime, workspace, user);
            reservationRepository.save(reservation);
            if (i % 2 == 0) {
                startDate = startDate.plusDays(1);
            } else {
                startTime = startTime.plusHours(faker.number().numberBetween(1, 3));
            }
        }
    }
}
