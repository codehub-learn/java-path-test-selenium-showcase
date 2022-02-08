package helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestVariableHelpers {

    public static String getParameter(String variableName) throws IOException {
        String propertyPath = System.getProperty("user.dir")+"\\src\\test\\java\\resources\\property_files\\enviroment.properties";

        try (InputStream input = new FileInputStream(propertyPath)) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            return prop.getProperty(variableName);
        }
    }
}
