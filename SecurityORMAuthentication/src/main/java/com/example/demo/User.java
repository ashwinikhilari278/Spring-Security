package com.example.demo;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Data
@AllArgsConstructor
@Table(name = "User_Table")
public class User {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Integer userId;
	private String userName;
	private String password;
	private String userEmail;
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "roles_tab" ,joinColumns = @JoinColumn(name = "userId"))
	@Column(name = "userRoles")
	private Set<String> userRoles;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public Set<String> getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(Set<String> userRoles) {
		this.userRoles = userRoles;
	}
	public User() {
		super();
	}
	public User(String userName, String password, String userEmail, Set<String> userRoles) {
		super();
		this.userName = userName;
		this.password = password;
		this.userEmail = userEmail;
		this.userRoles = userRoles;
	}
	
	
}
