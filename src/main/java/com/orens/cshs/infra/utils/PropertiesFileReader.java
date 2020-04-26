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
            LoggerHandler.getInstance().log(ReportLevel.ERROR,"unable to locate "+ propertiesFileName +" file", e);
        } catch (IOException e) {
            LoggerHandler.getInstance().log(ReportLevel.ERROR,"unable to load "+ propertiesFileName +" file", e);
        }
    }


    public static String getMyName() {
        return properties.getProperty("my.name");
    }

    public static Integer getBoardHeight() {
        return Integer.parseInt(properties.getProperty("board.height"));
    }

    public static Integer getBoardWidth() {
        return Integer.parseInt(properties.getProperty("board.width"));
    }

    public static Integer getFrameHeight() {
        return Integer.parseInt(properties.getProperty("frame.height"));
    }

    public static Integer getFrameWidth() {
        return Integer.parseInt(properties.getProperty("frame.width"));
    }

    public static Long getTickTimeInMilliseconds() {
        return Long.parseLong(properties.getProperty("time.milliseconds.tick"));
    }

    public static Long getDrawTimeInMilliseconds() {
        return Long.parseLong(properties.getProperty("time.milliseconds.draw"));
    }

    public static Long getIsolationTimeInMilliseconds() {
        return Long.parseLong(properties.getProperty("time.milliseconds.isolation"));
    }

    public static Integer getStepDistance() {
        return Integer.parseInt(properties.getProperty("step.pixel.distance"));
    }

    public static Integer getInitialAmountOfHealthyPeople() {
        return Integer.parseInt(properties.getProperty("persons.amount.healthy"));
    }

    public static Integer getInitialAmountOfCarryingPeople() {
        return Integer.parseInt(properties.getProperty("persons.amount.carrying"));
    }

    public static Integer getInitialAmountOfSickPeople() {
        return Integer.parseInt(properties.getProperty("persons.amount.sick"));
    }

    public static Integer getInitialAmountOfInspectors() {
        return Integer.parseInt(properties.getProperty("persons.amount.inspectors"));
    }

    public static Integer getSickTemperatureThreshold() {
        return Integer.parseInt(properties.getProperty("persons.sick.temperature.threshold"));
    }
    public static Integer getInspectorSickThreshold() {
        return Integer.parseInt(properties.getProperty("inspector.sick.threshold"));
    }


    public static Integer getContagiousRadius() {
        return Integer.parseInt(properties.getProperty("contagious.pixel.radius"));
    }
    public static Long getContagiousTimeInMilliseconds() {
        return Long.parseLong(properties.getProperty("contagious.time"));
    }


}
