package com.testframework.corelibrary.miscalleneous;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

// File related methods
public class FileMethods {

    //get Properties file object based on its path to fetch the test data
    public static Properties getPropertiesObject(String file) throws IOException {
        FileReader reader=new FileReader(file);
        Properties properties=new Properties();
        properties.load(reader);
        return properties;
    }

}
