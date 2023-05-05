package com.github;


/**
 * Revision History:
 * Date            Author           Task ID                         Notes
 * ==========   =================   ==============  ===============================================
 * 2023.05.04   Mahsa
 */
public class Main {

    public static void main(String[] args) throws Exception {
        String hash = DigitalSign.validate();
        DigitalSign.sign(hash);
    }
}
