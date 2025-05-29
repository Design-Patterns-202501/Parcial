package com.patrones.factory;

import com.patrones.encryption.IEncrypt;

public interface IConcreteFactory {
    public IEncrypt getEncryptService();
}
