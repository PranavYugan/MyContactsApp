package com.seveneleven.mycontactsapp.user.model;

import java.util.List;

import com.seveneleven.mycontactsapp.contacts.Contacts;

public abstract class User {

	protected String email;
	protected String password;
	protected String userName;
	protected String hashedPassword;
	List<Contacts> contacts;

	
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	


	public String getHashedPassword() {
		return hashedPassword;
	}


	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}


	public abstract String getUserType();
}