package com.seveneleven.mycontactsapp.user.auth;
import com.seveneleven.mycontactsapp.user.model.User;
import com.seveneleven.mycontactsapp.user.utilities.PasswordHasher;

import java.util.HashMap;

public class BasicAuth {

    public static boolean authenticate(HashMap<String, User> map, String email,String enter_pass) {
    	String pass_hash=PasswordHasher.hash(enter_pass);
        if (map.containsKey(email) && map.get(email).getHashedPassword().equals(pass_hash)) {
            System.out.println("Basic authentication successful!");
            return true;
        } else {
            System.out.println("Basic authentication failed!");
            return false;
        }
    }
}