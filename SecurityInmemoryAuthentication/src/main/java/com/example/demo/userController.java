package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class userController {

	@GetMapping("/home")
	public String showhome()
	{
		return "home";
	}
	@GetMapping("/loginPage")
	public String showlogin()
	{
		return "loginPage";
	}
	@GetMapping("/hello")
	public String showhello()
	{
		return "hello";
	}
	
}
