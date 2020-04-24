package com.orens.cshs.infra.utils;

import com.orens.cshs.infra.logger.LoggerHandler;
import com.orens.cshs.infra.logger.ReportLevel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileReader {

    protected static final String propertiesFileName = "cshs.properties";

    protected static Properties properties;

    static {
        properties = new Properties();
        try {
            LoggerHandler.getInstance().log(ReportLevel.INFO, "loading .properties file named: "+ propertiesFileName);
            //properties.load(new FileReader(".\\src\\main\\resources\\configurations\\"+propertiesFileName));
            properties.load(new FileReader("."+ File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"configurations"+File.separator+ propertiesFileName));
        } catch (FileNotFoundException e) {
            LoggerHandler.getInstance().log(ReportLevel.ERROR,"unable to locate "+ propertiesFileName +" file");
            e.printStackTrace();
        } catch (IOException e) {
            LoggerHandler.getInstance().log(ReportLevel.ERROR,"unable to load "+ propertiesFileName +" file");
            e.printStackTrace();
        }
    }


    public static String getMyName() {
        return properties.getProperty("my.name");
    }




}
