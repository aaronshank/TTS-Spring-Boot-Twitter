package com.tts.techtalenttwitter.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.tts.techtalenttwitter.model.User;
import com.tts.techtalenttwitter.service.UserService;

// We are saying by adding the Controller annotation is that we are going to handle web requests in this class
@Controller
public class AuthorizationController {
	@Autowired
	private UserService userService;

	// Specify what url (endpoints) do we want to present for this page

	@GetMapping(value = "/login")
	public String login() {
		return "login";
	}

	@GetMapping(value = "/signup")
	public String registration(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "registration";
	}

	@PostMapping(value = "/signup")
	public String createNewUser(@Valid User user, BindingResult bindingResult, Model model) {
		User userExists = userService.findByUsername(user.getUsername());
		if (userExists != null) {
			bindingResult.rejectValue("username", "error.user", "Username is already taken");
		}
		if (!bindingResult.hasErrors()) {
			userService.saveNewUser(user);
			model.addAttribute("success", "Sign up successful!");
			model.addAttribute("user", new User());
		}
		return "registration";
	}
}
