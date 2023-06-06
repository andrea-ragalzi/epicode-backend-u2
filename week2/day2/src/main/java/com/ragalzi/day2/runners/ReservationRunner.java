package com.ragalzi.day2.runners;

import java.util.Random;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ragalzi.day2.models.Reservation;
import com.ragalzi.day2.models.User;
import com.ragalzi.day2.models.Workspace;
import com.ragalzi.day2.services.ReservationService;
import com.ragalzi.day2.services.UserService;
import com.ragalzi.day2.services.WorkspaceService;

@Component
@Order(4)
public class ReservationRunner implements CommandLineRunner {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private UserService userService;

    @Autowired
    private WorkspaceService workspaceService;

    @Value("${reservations_quantity}")
    private int quantity;

    @Override
    public void run(String... args) throws Exception {
        List<User> users = userService.getAllWithLessThanTwoReservations();
        if(users.isEmpty()) {
            return;
        }
        for (int i = 0; i < quantity; i++) {
            User user = users.get(new Random().nextInt(users.size()));
            this.saveReservationFake(user);
        }
    }

    private void saveReservationFake(User user) {
        Reservation reservation;
        Workspace workspace;
        List<Workspace> workspaces = this.workspaceService.getAll();
        do {
            reservation = this.reservationService.createReservationFake();
            workspace = workspaces.get(new Random().nextInt(workspaces.size()));
            reservation.setUser(user);
        } while (!this.workspaceService.isWorkspaceAvailable(
                workspace, reservation));
        reservation.setWorkspace(workspace);
        this.reservationService.save(reservation);
    }
}
