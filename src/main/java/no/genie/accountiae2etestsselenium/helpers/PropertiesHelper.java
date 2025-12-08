package no.genie.accountiae2etestsselenium.helpers;

import javax.imageio.IIOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;

public class PropertiesHelper {
    private static Properties properties;
    private static String linkFile;
    private static FileInputStream file;
    private static String relPropertiesFilePathDefault = "src/test/resources/configs.properties";

    public static Properties getAllFiles() {
        LinkedList<String> files = new LinkedList<>();
        files.add("src/test/resources/configs.properties");
        try {
            properties = new Properties();
            for (String f : files) {
                Properties temProp = new Properties();
                linkFile = System.getProperty("user.dir") + File.separator + f;
                file = new FileInputStream(linkFile);
                temProp.load(file);
                properties.putAll(temProp);
            }
            return properties;
        } catch (IOException e) {
            return properties;
        }
    }

    public static String getValue(String key) {
        String keyValue = null;
        try {
            if (file == null) {
                properties = new Properties();
                linkFile = System.getProperty("user.dir") + File.separator + relPropertiesFilePathDefault;
                file = new FileInputStream(linkFile);
                properties.load(file);
                file.close();
            }
            keyValue = properties.getProperty(key);
        } catch (Exception e) {
            System.out.println("Cannot read file: " + e.getMessage());
        }
        return keyValue;
    }
}
