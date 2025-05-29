package com.patrones.implementation;

public interface IMessage {
    public String encryptMessage(String message, String password) throws Exception;
    public String getMethodClassName();
}
