package com.orens.cshs.infra.utils;

import com.orens.cshs.infra.logger.LoggerHandler;
import com.orens.cshs.infra.logger.ReportLevel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


/**
 * this class reads the .properties file
 */
public class PropertiesFileReader {

    protected static final String propertiesFileName = "cshs.properties";

    protected static Properties properties;

    static {
        properties = new Properties();
        try {
            //LoggerHandler.getInstance().log(ReportLevel.Trace, "loading .properties file named: "+ propertiesFileName);

            //properties.load(new FileReader(".\\src\\main\\resources\\configurations\\"+propertiesFileName));
            properties.load(new FileReader("."+ File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"configurations"+File.separator+ propertiesFileName));
        } catch (FileNotFoundException e) {
            LoggerHandler.getInstance().log(ReportLevel.ERROR,"unable to locate "+ propertiesFileName +" file", e);
        } catch (IOException e) {
            LoggerHandler.getInstance().log(ReportLevel.ERROR,"unable to load "+ propertiesFileName +" file", e);
        }
    }


    public static String getMyName() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered PropertiesFileReader.getMyName()");
        return properties.getProperty("my.name");
    }

    public static Integer getBoardHeight() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered PropertiesFileReader.getBoardHeight()");
        return Integer.parseInt(properties.getProperty("board.height"));
    }

    public static Integer getBoardWidth() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered PropertiesFileReader.getBoardWidth()");
        return Integer.parseInt(properties.getProperty("board.width"));
    }

    public static Integer getFrameHeight() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered PropertiesFileReader.getFrameHeight()");
        return Integer.parseInt(properties.getProperty("frame.height"));
    }

    public static Integer getFrameWidth() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered PropertiesFileReader.getFrameWidth()");
        return Integer.parseInt(properties.getProperty("frame.width"));
    }

    public static Long getTickTimeInMilliseconds() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered PropertiesFileReader.getTickTimeInMilliseconds()");
        return Long.parseLong(properties.getProperty("time.milliseconds.tick"));
    }

    public static Long getDrawTimeInMilliseconds() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered PropertiesFileReader.getDrawTimeInMilliseconds()");
        return Long.parseLong(properties.getProperty("time.milliseconds.draw"));
    }

    public static Long getIsolationTimeInMilliseconds() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered PropertiesFileReader.getIsolationTimeInMilliseconds()");
        return Long.parseLong(properties.getProperty("time.milliseconds.isolation"));
    }

    public static Integer getStepDistance() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered PropertiesFileReader.getStepDistance()");
        return Integer.parseInt(properties.getProperty("step.pixel.distance"));
    }

    public static Integer getInitialAmountOfHealthyPeople() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered PropertiesFileReader.getInitialAmountOfHealthyPeople()");
        return Integer.parseInt(properties.getProperty("persons.amount.healthy"));
    }

    public static Integer getInitialAmountOfCarryingPeople() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered PropertiesFileReader.getInitialAmountOfCarryingPeople()");
        return Integer.parseInt(properties.getProperty("persons.amount.carrying"));
    }

    public static Integer getInitialAmountOfSickPeople() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered PropertiesFileReader.getInitialAmountOfSickPeople()");
        return Integer.parseInt(properties.getProperty("persons.amount.sick"));
    }

    public static Integer getInitialAmountOfInspectors() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered PropertiesFileReader.getInitialAmountOfInspectors()");
        return Integer.parseInt(properties.getProperty("persons.amount.inspectors"));
    }

    public static Integer getSickTemperatureThreshold() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered PropertiesFileReader.getSickTemperatureThreshold()");
        return Integer.parseInt(properties.getProperty("persons.sick.temperature.threshold"));
    }
    public static Integer getInspectorSickThreshold() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered PropertiesFileReader.getInspectorSickThreshold()");
        return Integer.parseInt(properties.getProperty("inspector.sick.threshold"));
    }

    public static Integer getContagiousRadius() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered PropertiesFileReader.getContagiousRadius()");
        return Integer.parseInt(properties.getProperty("contagious.pixel.radius"));
    }
    public static Long getContagiousTimeInMilliseconds() {
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered PropertiesFileReader.getContagiousTimeInMilliseconds()");
        return Long.parseLong(properties.getProperty("contagious.time"));
    }


}
