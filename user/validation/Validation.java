package com.seveneleven.mycontactsapp.user.validation;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
	public static boolean emailValidation(String email) {
		String emailRegex = "^[A-Za-z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

		Pattern pattern = Pattern.compile(emailRegex);
		if (email == null) {
			return false;
		}
		return pattern.matcher(email).matches();

	}

	public static boolean isValidPassword(String password) {
		String passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d)\\S{8,}$";

		return Pattern.matches(passwordRegex, password);
	}

}