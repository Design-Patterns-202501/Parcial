package com.patrones.encryption;

import java.security.MessageDigest;

public class SHAEncrypter implements IEncrypt {

	@Override
	public String encrypt(String message, String password) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedHash = digest.digest(message.getBytes());

        StringBuilder builder = new StringBuilder(2*encodedHash.length);
        for (int i = 0; i < encodedHash.length; i++) {
            String hex = Integer.toHexString(0xff & encodedHash[i]);
            if (hex.length() == 1) builder.append("0");
            builder.append(hex);
        }
        return builder.toString();
	}

    
}
