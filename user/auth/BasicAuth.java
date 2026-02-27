package com.seveneleven.mycontactsapp.user.auth;

import java.util.HashMap;

public class BasicAuth {

    public static boolean authenticate(HashMap<String, String> map, String email, String password) {
        if (map.containsKey(email) && map.get(email).equals(password)) {
            System.out.println("Basic authentication successful!");
            return true;
        } else {
            System.out.println("Basic authentication failed!");
            return false;
        }
    }
}