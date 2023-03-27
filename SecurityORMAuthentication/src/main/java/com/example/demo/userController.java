package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class userController {

	@Autowired
	private userService service;
	@PostMapping("/save")
	public String saveUser(Model model, @ModelAttribute User user)
	{
		Integer id= service.saveUser(user);
		String message= "user with id "+id+" is created";
		model.addAttribute("message", message);
		return "register";
	}
	
	//1. show Register page
		@GetMapping("/register")
		public String showReg() {
			return "register";
		}
}
