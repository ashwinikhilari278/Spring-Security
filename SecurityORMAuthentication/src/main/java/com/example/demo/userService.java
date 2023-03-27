package com.example.demo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class userService implements UserDetailsService{

	@Autowired
	private UserRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder passencoder;
	public Integer saveUser(User user)
	{
		String encodedPassword= passencoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		return repo.save(user).getUserId();
		
	}
	
	public Optional<User> findOneUser(Integer id)
	{
		return repo.findById(id);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> opt= repo.findByUserEmail(username);
		if(opt.isEmpty())
		{
			throw new UsernameNotFoundException(username +" not exist");
		}
		User user=opt.get();
		List<GrantedAuthority> authorities= user.getUserRoles().stream()
				.map(role->new SimpleGrantedAuthority(role))
				.collect(Collectors.toList());
		return new org.springframework.security.core.userdetails.User(username,user.getPassword(),authorities);
			
	
	}
}
