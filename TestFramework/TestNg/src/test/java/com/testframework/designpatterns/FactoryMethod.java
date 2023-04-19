package com.testframework.designpatterns;

public class FactoryMethod {
    public FactoryParent getFactoryObject(String factoryType)
    {
        if(factoryType == null){
            return null;
        }
        else if(factoryType.equalsIgnoreCase("factory1")) {
            return new Factory1();
        }
        else if(factoryType.equalsIgnoreCase("factory2")){
            return new Factory2();
        }
        else {
            return null;
        }
    }
}
