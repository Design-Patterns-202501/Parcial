package com.patrones.encryption.factories;

import com.patrones.encryption.AESEncrypter;
import com.patrones.encryption.IEncrypt;
import com.patrones.factory.IConcreteFactory;

public class AESFactory implements IConcreteFactory {

	@Override
	public IEncrypt getEncryptService() {
        return new AESEncrypter();
	}

    
}
