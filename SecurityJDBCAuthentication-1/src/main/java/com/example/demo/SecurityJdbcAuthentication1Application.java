package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SecurityJdbcAuthentication1Application {

	public static void main(String[] args) {
		SpringApplication.run(SecurityJdbcAuthentication1Application.class, args);
		
/* before running the code run below queries at sql databse
 * SET FOREIGN_KEY_CHECKS = 0;
    TRUNCATE TABLE authorities;
    TRUNCATE TABLE users;
    SET FOREIGN_KEY_CHECKS = 1;
 */
	}

}
