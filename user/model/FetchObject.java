package com.seveneleven.mycontactsapp.user.model;

public class FetchObject {
	public static User createObject(String userType,String email,String userName,String password) {
		if(userType.equalsIgnoreCase("FREE")) {
			return new FreeUser(email,password,userName);
		}
		return new PremiumUser(email,password,userName);
	}
}