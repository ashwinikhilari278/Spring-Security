package com.example.demo.Utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {

	@Value("${app.secret}")
	private String secretkey;
	
	private String generateToken(Map<String,Object> claims,String username)
	{
		return Jwts.builder().setClaims(claims)
				.setSubject(username)
				.setIssuer("ABC.org")
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+TimeUnit.MINUTES.toMillis(10)))
				.signWith(SignatureAlgorithm.HS256, secretkey.getBytes())
				.compact();
	}
	
	public String generateToken(String username)
	{
		Map<String,Object> claims=new HashMap<>();
		return generateToken(claims,username);
	}
	
	public String getUsername(String token)
	{
		return getClaims(token).getSubject();
	}
	private Claims getClaims(String token)
	{
		return Jwts.parser().setSigningKey(secretkey.getBytes()).parseClaimsJws(token).getBody();
				
	}
}
