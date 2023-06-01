
package com.ragalzi.project.services;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ragalzi.project.enumerates.UserRole;
import com.ragalzi.project.models.Reservation;
import com.ragalzi.project.models.User;
import com.ragalzi.project.models.Workspace;
import com.ragalzi.project.repositories.ReservationRepository;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public void deleteReservation(Long reservationId, User user) throws IllegalAccessException {
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);

        if (reservation == null) {
            throw new IllegalArgumentException("Reservation not found");
        }

        if (user.getRole() == UserRole.ADMIN || reservation.getUser().equals(user)) {
            reservationRepository.delete(reservation);
        } else {
            throw new IllegalAccessException("User is not authorized to delete this reservation");
        }
    }

    public void createReservation(LocalDate date, LocalTime startTime, LocalTime endTime, Workspace workspace,
            User user) throws IllegalArgumentException {
        if (user.getRole() == UserRole.ADMIN || canUserReserve(user, date, startTime, endTime)) {
            Reservation reservation = new Reservation();
            reservation.setDate(date);
            reservation.setStartTime(startTime);
            reservation.setEndTime(endTime);
            reservation.setWorkspace(workspace);
            reservation.setUser(user);
            reservationRepository.save(reservation);
        } else {
            throw new IllegalArgumentException("User cannot reserve workspace for this time slot");
        }
    }

    private boolean canUserReserve(User user, LocalDate date, LocalTime startTime, LocalTime endTime) {
        int count = 0;
        for (Reservation reservation : user.getReservations()) {
            if (reservation.getDate().equals(date)) {
                if (reservation.getStartTime().isBefore(endTime) && reservation.getEndTime().isAfter(startTime)) {
                    count++;
                }
            }
        }
        return count < 2;
    }
}