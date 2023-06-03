package com.ragalzi.project.configurations;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.ragalzi.project.models.User;
import com.github.javafaker.Faker;
import com.ragalzi.project.enumerates.UserRole;

@Configuration
public class UserConfiguration {

	@Value("${admin.username}")
	private String username;
	@Value("${admin.fullname}")
	private String fullname;
	@Value("${admin.email}")
	private String email;
	@Value("${admin.password}")
	private String password;

	@Bean("UserAdminBean")
	@Scope("singleton")
	User userAdmin() {
		return new User(
				this.username, this.fullname, this.email,
				this.password, UserRole.ADMIN);
	}

	@Bean("UserNoParamsBean")
	@Scope("prototype")
	User userNoParams() {
		return new User();
	}

	@Bean("UserWithParamsBean")
	@Scope("prototype")
	User userWithParams(
			String username, String fullname, String email, String password) {
		return new User(username, fullname, email, password, UserRole.USER);
	}

	@Bean("UserFakeBean")
	@Scope("prototype")
	User userFake() {
		Faker faker = new Faker(new Locale("it"));
		String fullname = faker.name().fullName();
		String username;
		try {
			username = fullname.split(" ")[0].toLowerCase();
		} catch (Exception e) {
			username = fullname.toLowerCase();
		}
		String email = String.format(
				"%s@%s", username, faker.internet().domainName().trim());
		String password = faker.internet().password();
		UserRole role = UserRole.USER;
		User user = new User();
		user.setUsername(username);
		user.setFullname(fullname);
		user.setEmail(email);
		user.setPassword(password);
		user.setRole(role);
		return user;
	}

}