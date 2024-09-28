package com.zouzanyan.zanmanager.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class CookieUtil {
    private static final String SECRET_KEY = "zouzanyan";
    private static final String ALGORITHM = "HmacSHA256";

    /**
     * @param value 原始字符串
     * @return signValue 原始字符串base64:签名base64
     * */
    public static String signValue(String value) {
        try {
            Mac mac = Mac.getInstance(ALGORITHM);
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), ALGORITHM);
            mac.init(secretKey);
            byte[] signature = mac.doFinal(value.getBytes(StandardCharsets.UTF_8));
            return value + ":" + Base64.getEncoder().encodeToString(signature);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean verifySignedValue(String signedValue) {
        try {
            String[] parts = signedValue.split(":");
            if (parts.length == 2) {
                Mac mac = Mac.getInstance(ALGORITHM);
                SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), ALGORITHM);
                mac.init(secretKey);
                byte[] signature = mac.doFinal(parts[0].getBytes(StandardCharsets.UTF_8));
                return Base64.getEncoder().encodeToString(signature).equals(parts[1]);
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}