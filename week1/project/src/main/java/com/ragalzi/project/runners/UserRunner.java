package com.ragalzi.project.runners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ragalzi.project.enumerates.UserRole;
import com.ragalzi.project.models.User;
import com.ragalzi.project.repositories.UserRepository;;

@Component
@Order(1)
public class UserRunner implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    public static final int QUANTITY = 100;

    @Override
    public void run(String... args) throws Exception {
        User admin = User.randomUser();
        admin.setRole(UserRole.ADMIN);
        userRepository.save(admin);
        for (int i = 0; i < UserRunner.QUANTITY - 1; i++) {
            User newUser = User.randomUser();
            int userNumber = 0;
            while (userRepository.findByUsername(newUser.getUsername()) != null) {
                newUser.setUsername(
                        String.format(
                                "%s_%d", newUser.getUsername(), userNumber++));
                newUser.setEmail(
                    String.format(
                            "%s_%d@example.com", newUser.getUsername(), userNumber
                    )
                );
            }
            userRepository.save(newUser);
        }

    }

}
