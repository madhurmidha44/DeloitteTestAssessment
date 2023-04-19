package com.testframework.designpatterns;

//Singleton is a creational design pattern that lets you ensure that a class has only one instance, while providing a global access point to this instance.
public class SingletonDesignPattern {
    private static SingletonDesignPattern obj=new SingletonDesignPattern();//Early, instance will be created at load time
    private SingletonDesignPattern(){}

    public static SingletonDesignPattern getObject(){
        return obj;
    }

    //Sample method to create DB connection
    public static void createDBConnection(){
        //write your code
    }

}
