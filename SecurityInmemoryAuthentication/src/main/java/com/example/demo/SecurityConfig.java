package com.example.demo;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain configurePath(HttpSecurity http) throws Exception
	{
		http.authorizeHttpRequests(request-> request.antMatchers("/","/home").permitAll()
				.anyRequest().authenticated()).formLogin(form->form.loginPage("/loginPage").permitAll())
				.logout(logout->logout.permitAll());
		return http.build();
	}
	
	@Bean
	public UserDetailsService userDetailsService()
	{
		UserDetails user1= User.withDefaultPasswordEncoder().username("ashu").password("saibaba").roles("Admin").build();
		return new InMemoryUserDetailsManager(user1);
	}
}
