package com.example.online_voting_app;

import android.os.Build;
import android.util.Log;

import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class PasswordGenerator {
    //defining the list of characters the password can contain
    static String generatePassword(int length) {
        final String charList = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        //randomly choosing a character by each iteration of the loop and appending to the
        //StringBuilder instance
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(charList.length());
            password.append(charList.charAt(randomIndex));
        }

        return password.toString();
    }


    //Open source code used !!!

}
