package com.seveneleven.mycontactsapp.main;
import java.util.*;

import com.seveneleven.mycontactsapp.user.auth.BasicAuth;
import com.seveneleven.mycontactsapp.user.auth.OAuth;
import com.seveneleven.mycontactsapp.user.model.FetchObject;
import com.seveneleven.mycontactsapp.user.model.User;
import com.seveneleven.mycontactsapp.user.utilities.PasswordHasher;
import com.seveneleven.mycontactsapp.user.validation.Validation;

public class MyContactsApp{
	
	public static void handler() {
		Scanner scanner = new Scanner(System.in);
		HashMap<String, String> map = new HashMap<>();

		System.out.print("Enter Email: ");
		String email = scanner.nextLine();
		if (! Validation.emailValidation(email)) {
			System.out.println("Enter a valid email");
		}

		System.out.print("Enter Password: ");
		String password = scanner.nextLine();
		if (!Validation.isValidPassword(password)) {
		}

		System.out.print("Enter User Type: ");
		String type = scanner.nextLine();

		System.out.print("Enter Username: ");
		String username = scanner.nextLine();

		String hashedPassword = PasswordHasher.hash(password);

		User access = FetchObject.createObject(type, email, username, password);

		System.out.println("Registered");
		System.out.println("Email: " + access.getEmail());
		System.out.println("Password Hash: " + hashedPassword);
		System.out.println("Username: " + access.getUserName());
		System.out.println("Type: " + access.getUserType());

		map.put(access.getEmail(), access.getPassword());

		System.out.println();
		System.out.println("Press 1 to Login:");
		int choice=scanner.nextInt();
		System.out.println("Choose the type of Authentication:");
		System.out.println("1.Basic Authentication");
		System.out.println("2.OAuthentication");

		int choice2=scanner.nextInt();
		scanner.nextLine();
		
		if(choice2==1) {
			BasicAuth.authenticate(map, email, password);
		}
		else if(choice2==2){
			if(type.equalsIgnoreCase("FREE")) {
				System.out.println("OAuth only for premium users");
			}
			else {
				System.out.println("Token is being generated.");
				System.out.println("Your token is :" + OAuth.generateToken(email));
				System.out.println("To validate login kindly enter your allocated token:");
				String temp=scanner.nextLine();
				OAuth.checkAuthentication(email, temp);
			}
		}

	}


	public static void main(String args[]) {
		handler();
	}
}