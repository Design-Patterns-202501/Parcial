package com.patrones.factory;

import com.patrones.utils.PropsUtil;

public class AbstractFactory implements IAbstractFactrory {

	@Override
	public IConcreteFactory getFactory() {
        String className = new PropsUtil().loadProperties().getProperty("factory.implemented");
        try {
            return (IConcreteFactory) Class.forName(className).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}

    
}
