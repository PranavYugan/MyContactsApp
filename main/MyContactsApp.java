package com.seveneleven.mycontactsapp.main;

import java.util.*;

import com.seveneleven.mycontactsapp.user.model.FetchObject;
import com.seveneleven.mycontactsapp.user.model.User;
import com.seveneleven.mycontactsapp.user.utilities.PasswordHasher;
import com.seveneleven.mycontactsapp.user.validation.Validation;

public class MyContactsApp {

	public static void handler() {
		Scanner scanner=new Scanner(System.in);

		System.out.print("Enter Email: ");
		String email = scanner.nextLine();
		if(!Validation.emailValidation(email)) {
			System.out.println("Enter a valid email");
			return;
		}

		System.out.print("Enter Password: ");
		String password = scanner.nextLine();
		if(!Validation.isValidPassword(password)) {
			System.out.println("Enter a strong password");
			return;
		}

		System.out.print("Enter User Type: ");
		String type = scanner.nextLine();

		System.out.print("Enter Username: ");
		String userame = scanner.nextLine();



		String hashedPassword = PasswordHasher.hash(password);

		User access=FetchObject.createObject(type, email, userame, password);

		System.out.println("Registered");
		System.out.println("Email: " + access.getEmail());
		System.out.println("Password Hash: " + hashedPassword);
		System.out.println("Username:" + access.getUserName());
		System.out.println("Type: " + access.getUserType());
	}



	public static void main(String args[]) {

		handler();
	}

}