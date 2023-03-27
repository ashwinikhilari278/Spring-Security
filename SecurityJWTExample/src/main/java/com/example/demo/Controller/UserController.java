package com.example.demo.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.User;
import com.example.demo.Service.UserService;
import com.example.demo.Utils.JwtUtils;

import Payload.UserRequest;
import Payload.UserResponse;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userservice;
	
	@Autowired
	private JwtUtils jwtutil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/save")
	public ResponseEntity<String> saveUser(@RequestBody User user)
	{
		userservice.saveUser(user);
		return ResponseEntity.ok("user created");
	}
	@PostMapping("/login")
	public ResponseEntity<UserResponse> loginUser(@RequestBody UserRequest userRequest) {
		
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						userRequest.getUserName(), 
						userRequest.getUserPassword()
						)
				);
		String token = jwtutil.generateToken(userRequest.getUserName());
		
		return ResponseEntity.ok(new UserResponse(token, "Generated By Mr.RAGHU"));
	}
	
	@PostMapping("/welcome")
	public ResponseEntity<String> showUserData(Principal p) {
		System.out.println(p.getClass().getName());
		return ResponseEntity.ok("Hello :" + p.getName());
	}

}