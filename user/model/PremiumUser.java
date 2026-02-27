package com.seveneleven.mycontactsapp.user.model;

public class PremiumUser extends User {
	String userType="PREMIUM";
	public PremiumUser(String email,String password,String userName,String hashedPassword) {
		this.email=email;
		this.password=password;
		this.userName=userName;
		this.hashedPassword=hashedPassword;
	}
	
	public String getUserType() {
		return userType;
	}
}