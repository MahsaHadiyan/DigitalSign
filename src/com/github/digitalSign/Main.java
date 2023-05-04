package com.github.digitalSign;


import java.io.File;
import java.io.FileReader;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.Map;


/**
 * Revision History:
 * Date            Author           Task ID                         Notes
 * ==========   =================   ==============  ===============================================
 * 2023.05.04   Mahsa
 */
public class Main {

    public static void main(String[] args) throws Exception {
        File file = new File("doc.txt");
        FileReader fileReader = new FileReader(file);
        int ascii = fileReader.read();
        String plain = "";
        while (ascii != -1) {
            plain += (char) ascii;
            ascii = fileReader.read();
        }
        fileReader.close();
        String hash = MD5.getMD5(plain);

        Map<String, Key> map = RSA.getRSAKeys();
        PublicKey publicKey = (PublicKey) map.get("PublicKey");
        PrivateKey privateKey = (PrivateKey) map.get("PrivateKey");
        byte[] sign = RSA.getRSAEncryptByPrivateKey(privateKey, hash);
        String digitalSign = Base64.getEncoder().encodeToString(sign);
        sign = Base64.getMimeDecoder().decode(digitalSign);
        hash = RSA.getRSADecryptByPublicKey(publicKey, sign);
        String providedHash = MD5.getMD5(plain);
        System.out.println("hash = " + hash);
        System.out.println("providedHash = " + providedHash);
        if (hash.equals(providedHash)){
            System.out.println("TRUE");
        }
    }

    public static void main1(String[] args) {
        // Java 8
        // Using encode()
        byte[] encode = Base64.getEncoder().encode("Java2blog".getBytes());
        String result = new String(encode);
        System.out.println(result);

        // Using encodeToString() to get String directly
        String encodeToString = Base64.getEncoder().encodeToString("Java2blog".getBytes());
        System.out.println(encodeToString);
    }
}
