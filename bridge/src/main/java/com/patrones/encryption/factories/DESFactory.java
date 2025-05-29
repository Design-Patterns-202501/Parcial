package com.patrones.encryption.factories;

import com.patrones.encryption.DESEncrypter;
import com.patrones.encryption.IEncrypt;
import com.patrones.factory.IConcreteFactory;

public class DESFactory implements IConcreteFactory {

	@Override
	public IEncrypt getEncryptService() {
        return new DESEncrypter();
	}
    
}
