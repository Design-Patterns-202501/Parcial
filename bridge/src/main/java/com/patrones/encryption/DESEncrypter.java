package com.patrones.encryption;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DESEncrypter implements IEncrypt {
    @Override
    public String encrypt(String message, String password) throws Exception {
        DESKeySpec dks = new DESKeySpec(password.getBytes());
        SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
        SecretKey desKey = skf.generateSecret(dks);
        Cipher desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        desCipher.init(Cipher.ENCRYPT_MODE, desKey);
        Base64.Encoder encoder = Base64.getEncoder();
        String encodedString= encoder.encodeToString(desCipher.doFinal(message.getBytes()));
        return encodedString;
    };
};
