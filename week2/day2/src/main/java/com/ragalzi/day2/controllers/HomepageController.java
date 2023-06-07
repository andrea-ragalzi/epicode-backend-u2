package com.ragalzi.day2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ragalzi.day2.services.UserService;

@Controller
public class HomepageController {

	@Autowired
	UserService userService;

	@GetMapping("/")
	public String homepage() {
		return "homepage";
	}

}
