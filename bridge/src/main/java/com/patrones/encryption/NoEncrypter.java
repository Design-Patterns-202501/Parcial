package com.patrones.encryption;

public class NoEncrypter implements IEncrypt {

	@Override
	public String encrypt(String message, String password) throws Exception {
        return message;
	}
};
