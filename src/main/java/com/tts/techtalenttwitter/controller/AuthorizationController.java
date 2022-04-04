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

//We are saying by adding the Controller annotation
//is that we are going to handle web requests in this class.
@Controller
public class AuthorizationController {

  @Autowired
  private UserService userService;

  // Specify what url (endpoints) do we want to present this page for:
  // This is going a login form
  @GetMapping(value = "/login")
  public String login() {
    return "login"; // return value is the name of a template we'll use
                    // for a web page.
  }

  // This is going to present a registration form
  @GetMapping(value = "/signup")
  public String registration(Model model) {
    User user = new User();
    model.addAttribute("user", user);
    return "registration";
  }

  // When we signup with PostMapping that is going to be an actual
  // attempt to signup
  @PostMapping(value = "/signup")
  public String createNewUser(@Valid User user, BindingResult bindingResult, Model model) {
    // user at this point holds the input form data
    // that the user has set up for registration.

    // Check to see if the user already exists...
    User foundUser = userService.findByUsername(user.getUsername());
    if (foundUser != null) {
      // User already exists.
      bindingResult.rejectValue("username", "error.user", "Username is already taken");

    }
    if (!bindingResult.hasErrors()) {
      // Process a new user.
      userService.saveNewUser(user);
      model.addAttribute("success", "Sign up successful!");
      User user2 = new User();
      model.addAttribute("user", user2);
    }
    return "registration";
  }

}