package com.seveneleven.mycontactsapp.user.auth;

import java.util.HashMap;
import java.util.UUID;

public class OAuth {

    private static HashMap<String, String> tokenStore = new HashMap<>();

    public static String generateToken(String email) {
        String token = UUID.randomUUID().toString();
        tokenStore.put(email, token);
        return token;
    }

    public static void checkAuthentication(String email, String token) {
        if (tokenStore.containsKey(email) && tokenStore.get(email).equals(token)) {
            System.out.println("OAuth login successful!");
        } 
        else {
            System.out.println("OAuth login failed!");
        }
    }
}