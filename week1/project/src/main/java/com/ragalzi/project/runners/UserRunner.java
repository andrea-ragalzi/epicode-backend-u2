package com.ragalzi.project.runners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ragalzi.project.models.User;
import com.ragalzi.project.services.UserService;;

@Component
@Order(1)
public class UserRunner implements CommandLineRunner {
    @Autowired
    private UserService userService;

    @Value("${users_quantity}")
    private int quantity;

    @Override
    public void run(String... args) throws Exception {
        boolean adminExists = userService.existsAdminUser();
        if (!adminExists) {
            User admin = userService.createAdminUser();
            userService.save(admin);
        }
        for (int i = 0; i < this.quantity - 1; i++) {
            this.saveUserFake();
        }
    }

    private void saveUserFake() {
        User user;
        do {
            user = userService.createUserFake();
        } while (userService.getByUsername(user.getUsername()) != null);
        userService.save(user);
    }

}
