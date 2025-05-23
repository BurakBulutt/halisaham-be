package com.bitirmeodev.halisahambe.library.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Sha256Encoder implements PasswordEncoder {

    @Value("${encoder.salt}")
    private String salt;
    private static final String ALGORITM = "SHA-256";

    @Override
    public String encode(CharSequence rawPassword) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ALGORITM);
            messageDigest.update(salt.getBytes());
            byte[] hash = messageDigest.digest(rawPassword.toString().getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encode(rawPassword).equals(encodedPassword);
    }
}
