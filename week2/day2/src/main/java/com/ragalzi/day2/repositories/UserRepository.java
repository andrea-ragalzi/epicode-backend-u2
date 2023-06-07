package com.ragalzi.day2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ragalzi.day2.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.role = 'ADMIN'")
    boolean existsAdminUser();

    @Query("SELECT u FROM User u WHERE (SELECT COUNT(r) FROM Reservation r WHERE r.user = u) < 2")
    List<User> findAllWithLessThanTwoReservations();

    public User findByEmailAndPassword(String email, String password);

}
