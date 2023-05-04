package com.github.digitalSign;

import java.security.MessageDigest;

/**
 * Created by Amirsam on 9/21/2016.
 */
public class MD5 {
    public static String getMD5(String text) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(text.getBytes());
        byte byteData[] = md.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
    private MD5()
    {

    }
}
