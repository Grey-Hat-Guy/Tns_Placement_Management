package com.project.placement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository repo;
	@GetMapping("/")
	public String signUpPage(Model model) {
		model.addAttribute("user", new User());
		return "index";
	}
	
	@PostMapping("/signup")
	public String signUpSubmit(User user) {
		repo.save(user);
		return "login_page";
	}
	
	@GetMapping("/login")
	public String loginPage(Model model) {
	    return "login_page";
	}
	
	@PostMapping("/login")
	public String loginSubmit(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
	    if (isValidUser(email, password)) {
	        return "dashboard_page";
	    } else {
	        // If invalid user, return to login page with error message
	        model.addAttribute("error", "Invalid username or password");
	        return "login_page";
	    }
	}

	@GetMapping("/signup")
	public String signupPage(Model model) {
		model.addAttribute("user", new User());
	    return "index";
	}
	
	private boolean isValidUser(String email, String password) {
	    // Retrieve user details from the database based on the provided username
	    User user = repo.findByEmail(email);

	    // Check if user exists and password matches
	    if (user != null && user.getPassword().equals(password)) {
	        return true; // Valid user
	    } else {
	        return false; // Invalid user
	    }
	}


}
