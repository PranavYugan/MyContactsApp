package com.seveneleven.mycontactsapp.main;
import java.util.*;

import com.seveneleven.mycontactsapp.contacts.Organization;
import com.seveneleven.mycontactsapp.contacts.Person;
import com.seveneleven.mycontactsapp.contacts.Contacts;
import com.seveneleven.mycontactsapp.user.auth.BasicAuth;
import com.seveneleven.mycontactsapp.user.auth.OAuth;
import com.seveneleven.mycontactsapp.user.model.FetchObject;
import com.seveneleven.mycontactsapp.user.model.User;
import com.seveneleven.mycontactsapp.user.utilities.PasswordHasher;
import com.seveneleven.mycontactsapp.user.validation.Validation;

public class MyContactsApp{
	
	public static void handler() {
		Scanner scanner = new Scanner(System.in);
		HashMap<String, User> map = new HashMap<>();
		System.out.print("Enter Email: ");
		String email = scanner.nextLine();
		if (! Validation.emailValidation(email)) {
			System.out.println("Enter a valid email");
			return;
		}

		System.out.print("Enter Password: ");
		String password = scanner.nextLine();
		if (!Validation.isValidPassword(password)) {
			System.out.println("Enter a valid Password: ");
			return;
		}

		System.out.print("Enter User Type: ");
		String type = scanner.nextLine();

		System.out.print("Enter Username: ");
		String username = scanner.nextLine();

		String hashedPassword = PasswordHasher.hash(password);
		
		User access = FetchObject.createObject(type, email, username, password,hashedPassword);

		System.out.println("Registered");
		System.out.println("Email: " + access.getEmail());
		System.out.println("Password Hash: " + hashedPassword);
		System.out.println("Username: " + access.getUserName());
		System.out.println("Type: " + access.getUserType());

		map.put(access.getEmail(), access);
		User loggedInUser = null;
		System.out.println();
		System.out.println("Press 1 to Login:");
		System.out.println("Press 2 to Change/Modify your information");
		int choice=scanner.nextInt();
		if(choice==1) {
			System.out.println("Choose the type of Authentication:");
			System.out.println("1.Basic Authentication");
			System.out.println("2.OAuthentication");
	
			int choice2=scanner.nextInt();
			scanner.nextLine();
			
			if(choice2==1) {
				System.out.println("Enter your password: ");
				String enter_pass=scanner.nextLine();
				BasicAuth.authenticate (map, access.getEmail(),enter_pass);
				loggedInUser = access;
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
					loggedInUser = access;
				}
			}
			Contacts person;
			if(loggedInUser != null) {
				System.out.println("Press 1 to add contacts, else press 2 :");
				int temp=scanner.nextInt();
				scanner.nextLine();
				if(temp==2) {
					return;
				}
				else {
					System.out.print("Enter contact type (person/organisation): ");
                    String ctype = scanner.nextLine();
                    System.out.print("Enter name: ");
                    String cname = scanner.nextLine();


                    if (ctype.equalsIgnoreCase("person")) {
                        System.out.print("Enter relationship type: ");
                        String relationship = scanner.nextLine();
                        person= new Person(cname, relationship);
                    } else {
                        System.out.print("Enter location: ");
                        String location = scanner.nextLine();
                        person= new Organization(cname, location);
                    }

                    System.out.print("How many phone numbers? ");
                    int pCount = scanner.nextInt();
                    scanner.nextLine();

                    for (int i = 0; i < pCount; i++) {
                        System.out.print("Enter phone: ");
                        String phone = scanner.nextLine();
                        person.addPhoneNumber(phone);
                    }

                    System.out.print("How many emails? ");
                    int eCount = scanner.nextInt();
                    scanner.nextLine();

                    for (int i = 0; i < eCount; i++) {
                        System.out.print("Enter email: ");
                        String newEmail = scanner.nextLine();
                        person.addEmail(newEmail);
                    }
                    
                    System.out.println("Contact Added.");
				}
			}
		}
		else if(choice==2) {
			System.out.println("Press 1 to Change your UserType.");
			System.out.println("Press 2 to Change your Password.");
			System.out.println("Press 3 to Change you Username.");
			int choice2=scanner.nextInt();
			scanner.nextLine();
			if(choice2==1) {
				System.out.println("Enter the Plan you want to change to:");
				String plan=scanner.nextLine();
				type=plan;
				User newaccess = FetchObject.createObject(type, access.getEmail(), access.getUserName(), access.getPassword(),access.getHashedPassword());
				map.put(newaccess.getEmail(), newaccess);
				loggedInUser=newaccess;
				System.out.println("Plan changed");
				
			}
			else if(choice==2) {
				System.out.println("Enter the Password you want to change:");
				String change_pass=scanner.nextLine();
				loggedInUser.setPassword(change_pass);
				System.out.println("Password Changed.");
			}
			else if(choice==3) {
				System.out.println("Enter the User Name you want to change:");
				String change_username=scanner.nextLine();
				loggedInUser.setUserName(change_username);
				System.out.println("User Name Changed.");
			}
			
		}

	}


	public static void main(String args[]) {
		handler();
	}
}