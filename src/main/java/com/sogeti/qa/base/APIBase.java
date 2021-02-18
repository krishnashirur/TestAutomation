package com.sogeti.qa.base;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class APIBase {
    public static WebDriver driver;
    public static String baseuri;
    public static String basepath;
    public static Properties prop;
    public static final Logger logger = Logger.getLogger(TestBase.class.getName());
    public static final String USER_DIR = "user.dir";

    public APIBase() {
        prop = new Properties();
        String configFileName = "config.properties";
        try {
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/sogeti/qa/config/" + configFileName);
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void apiInitialization() {
        baseuri=prop.getProperty("baseuri");
        basepath=prop.getProperty("basepath");
    }
}
