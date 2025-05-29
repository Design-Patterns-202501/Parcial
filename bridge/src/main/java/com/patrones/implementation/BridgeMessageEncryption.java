package com.patrones.implementation;

import com.patrones.encryption.IEncrypt;

public class BridgeMessageEncryption implements IMessage {
    private IEncrypt encryptMethod;

    public BridgeMessageEncryption(IEncrypt method) {
        this.encryptMethod = method;
    }

	@Override
	public String encryptMessage(String message, String password) throws Exception {
        return encryptMethod.encrypt(message, password);
	}

    @Override
    public String getMethodClassName() {
        return encryptMethod.getClass().getCanonicalName();
    }

}
