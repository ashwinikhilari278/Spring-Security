package com.example.demo;

import javax.sql.DataSource;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain configureAuth(HttpSecurity http) throws Exception
	{
		http.authorizeHttpRequests(request->request.antMatchers("/home","/").permitAll().
				antMatchers("/admin").hasAuthority("ADMIN").antMatchers("/customer").hasAnyAuthority("ADMIN","CUSTOMER")
				.anyRequest().authenticated())
		.formLogin(form->form.loginPage("/loginPage").permitAll().defaultSuccessUrl("/hello", true)).
		logout(logout->logout.permitAll());
		
		return http.build();
	}
	@Bean
	public UserDetailsService userDetailService(DataSource dataSource)
	{
		JdbcUserDetailsManager users=new JdbcUserDetailsManager(dataSource);
		
		UserDetails user1  = User.withUsername("sam")
				.password("$2a$10$98sW17qokkbsXmBop6dNcukPYT04MI6Bsxt.HqBiEue30x6yrhm7m")
				.authorities("ADMIN").build();
		UserDetails user2  = User.withUsername("ram")
				.password("$2a$10$mzChpIXOsZwHP0Wo4UAc9ecEX1fG14qG274HkedcTdXw8FnslvMOy")
				.authorities("CUSTOMER").build();
		
		users.createUser(user1);
		users.createUser(user2);
		
		return users;
		
	}
	
}
