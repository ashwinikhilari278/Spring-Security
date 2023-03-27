package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

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
	@GetMapping("/admin")
	public String helloAdmin()
	{
		return "AdminHello";
	}
	@GetMapping("/customer")
	public String helloCustomer()
	{
		return "CustomerHello";
	}
}
