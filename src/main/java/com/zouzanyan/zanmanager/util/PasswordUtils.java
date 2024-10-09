package com.zouzanyan.zanmanager.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordUtils {

    private static final String SALT = "zanmanagement";
    private static final String HMAC_SHA256_ALGORITHM = "HmacSHA256";

    // 生成加盐的HMAC-SHA256散列
    public static String generateSaltedHmacSha256(String password) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(SALT.getBytes(), HMAC_SHA256_ALGORITHM);
            Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
            mac.init(keySpec);
            byte[] hmacDigest = mac.doFinal(password.getBytes());
            return Base64.getEncoder().encodeToString(hmacDigest);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Error generating HMAC-SHA256 hash", e);
        }
    }

    // 验证提供的密码是否与存储的加盐HMAC-SHA256散列匹配
    public static boolean verifyPassword(String password, String storedHash) {
        String generatedHash = generateSaltedHmacSha256(password);
        return generatedHash.equals(storedHash);
    }
}

class aawdwad{
    public static void main(String[] args) {
        System.out.println(PasswordUtils.generateSaltedHmacSha256("123"));
        System.out.println(PasswordUtils.verifyPassword("1234", "h67YgOCyqK1nTEBl6Z0rYoQtdd2B2TVYrOShqby3lkk="));
    }
}