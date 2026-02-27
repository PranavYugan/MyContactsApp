package com.seveneleven.mycontactsapp.user.model;

public class FreeUser extends User {
	String userType="FREE";
	public FreeUser(String email,String password,String userName,String hashedPassword) {
		this.email=email;
		this.password=password;
		this.userName=userName;
		this.hashedPassword=hashedPassword;
	}
	
	public String getUserType() {
		return userType;
	}
}