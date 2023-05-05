package com.github;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.Map;

/**
 * Revision History:
 * Date            Author           Task ID                         Notes
 * ==========   =================   ==============  ===============================================
 * 2023.05.05   Mahsa
 */
public class DigitalSign {

    private static final DigitalSign digitalSign=new DigitalSign();

    public DigitalSign getInstance(){
        return digitalSign;
    }

    public static String validate() throws Exception {
        String plain = getPlain();
        return MD5.getMD5(plain);
    }

    public static String getPlain() throws IOException {
        File file = new File("doc.txt");
        FileReader fileReader = new FileReader(file);
        int ascii = fileReader.read();
        String plain = "";
        while (ascii != -1) {
            plain += (char) ascii;
            ascii = fileReader.read();
        }
        fileReader.close();
        return plain;
    }

    public static void sign(String hash) throws Exception {
        Map<String, Key> map = RSA.getRSAKeys();
        PublicKey publicKey = (PublicKey) map.get("PublicKey");
        PrivateKey privateKey = (PrivateKey) map.get("PrivateKey");
        byte[] sign = RSA.getRSAEncryptByPrivateKey(privateKey, hash);
        String digitalSign = Base64.getEncoder().encodeToString(sign);
        sign = Base64.getMimeDecoder().decode(digitalSign);
        hash = RSA.getRSADecryptByPublicKey(publicKey, sign);
        String providedHash = MD5.getMD5(DigitalSign.getPlain());
        System.out.println("hash = " + hash);
        System.out.println("providedHash = " + providedHash);
        if (hash.equals(providedHash)) {
            System.out.println("TRUE");
        }
    }
}
