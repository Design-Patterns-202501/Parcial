package com.patrones.encryption.factories;

import com.patrones.encryption.IEncrypt;
import com.patrones.encryption.SHAEncrypter;
import com.patrones.factory.IConcreteFactory;

public class SHAFactory implements IConcreteFactory {

	@Override
	public IEncrypt getEncryptService() {
        return new SHAEncrypter();
	}
    
}
