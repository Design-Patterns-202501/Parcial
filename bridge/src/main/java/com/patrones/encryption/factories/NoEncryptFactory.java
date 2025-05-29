package com.patrones.encryption.factories;

import com.patrones.encryption.IEncrypt;
import com.patrones.encryption.NoEncrypter;
import com.patrones.factory.IConcreteFactory;

public class NoEncryptFactory implements IConcreteFactory {

	@Override
	public IEncrypt getEncryptService() {
        return new NoEncrypter();
	}
    
}
