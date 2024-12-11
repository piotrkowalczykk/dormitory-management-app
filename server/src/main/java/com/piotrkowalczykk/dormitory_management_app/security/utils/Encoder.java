package com.piotrkowalczykk.dormitory_management_app.security.utils;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Component
public class Encoder {
    public String encode(String rawText) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(rawText.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException exception){
            throw new RuntimeException("encoder error");
        }
    }

    public boolean matches(String rawText, String encodedText){
        return encode(rawText).equals(encodedText);
    }
}
