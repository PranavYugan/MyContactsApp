package com.seveneleven.mycontactsapp.user.model;

public class PremiumUser extends User {
	String userType="PREMIUM";
	public PremiumUser(String email,String password,String userName) {
		this.email=email;
		this.password=password;
		this.userName=userName;
	}
	
	public String getUserType() {
		return userType;
	}
}