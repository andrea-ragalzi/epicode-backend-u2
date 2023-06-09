package com.ragalzi.project.security.runners;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ragalzi.project.security.enumerates.UserRole;
import com.ragalzi.project.security.models.Role;
import com.ragalzi.project.security.repositories.RoleRepository;
import com.ragalzi.project.security.repositories.UserRepository;
import com.ragalzi.project.security.services.AuthService;

@Component
public class AuthRunner implements ApplicationRunner {

	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	AuthService authService;

	private Set<Role> adminRole;
	private Set<Role> moderatorRole;
	private Set<Role> userRole;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("Run...");
		// Metodo da lanciare solo la prima volta
		// Serve per salvare i ruoli nel DB
		// setRoleDefault();

	}

	private void setRoleDefault() {
		Role admin = new Role();
		admin.setRole(UserRole.ADMIN);
		roleRepository.save(admin);

		Role moderator = new Role();
		moderator.setRole(UserRole.MODERATOR);
		roleRepository.save(moderator);

		Role user = new Role();
		user.setRole(UserRole.USER);
		roleRepository.save(user);

		// adminRole = new HashSet<Role>();
		// adminRole.add(admin);
		// adminRole.add(moderator);
		// adminRole.add(user);
		//
		// moderatorRole = new HashSet<Role>();
		// moderatorRole.add(moderator);
		// moderatorRole.add(user);
		//
		// userRole = new HashSet<Role>();
		// userRole.add(user);
	}

}
