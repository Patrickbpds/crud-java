package com.patrick.crud.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeneratePasswordUserAuthentication {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String passwordPure = "senha";
        String passwordEncrypted = encoder.encode(passwordPure);

        System.out.println("Encrypted Password: " + passwordEncrypted);
    }
}
