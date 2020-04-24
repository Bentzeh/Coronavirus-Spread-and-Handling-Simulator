package com.orens.cshs.infra.logger;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class LoggerHandler implements IReports {

    public enum TestStatus{
        PASS,
        FAIL
    }

    private static final Logger logger = LogManager.getLogger(LoggerHandler.class); // log4j2 that connected to report portal
    private static LoggerHandler instance;
    private static final String visualSeparator = "###################";
    public static TestStatus testStatus = TestStatus.PASS;
    private static ReportLevel logLevelMarkFailAndStopThreshold = ReportLevel.FATAL;
    private static ReportLevel logLevelMarkFailThreshold = ReportLevel.ERROR;
    //protected ReportDispatcher report = ReportManager.getInstance();; //difido

    private LoggerHandler(){

    }

    public static LoggerHandler getInstance() {
        if (null == instance) {
            instance = new LoggerHandler();
        }
        return instance;
    }


    /**
     *
     * checks the given log level inorder to mark the test status
     *
     * @param level the logging level
     * @param message the message object to log.
     */

    private void checkLevel(ReportLevel level, String message){
        if(level.intLevel() <= logLevelMarkFailAndStopThreshold.intLevel()){
            testStatus = TestStatus.FAIL;
            throw new RuntimeException("FATAL : Test deliberately stopped with message : " + message);
        }
        else if(level.intLevel() <= logLevelMarkFailThreshold.intLevel()){
            testStatus = TestStatus.FAIL;
        }
    }



    /**
     * indicate the beginning of a test
     *
     * @param description description of the test
     */
    public void startLevel(String description) {
        logger.log(Level.getLevel(ReportLevel.INFO.name()), visualSeparator+"___  "+description+"  ___"+visualSeparator);
    }



    /**
     * indicate the end of a test
     *
     */
    public void endLevel() {
        logger.log(Level.getLevel(ReportLevel.INFO.name()), visualSeparator+visualSeparator);
    }



    /**
     * Logs a message object with the given level.
     *
     * @param level the logging level
     * @param message the message object to log.
     */
    public void log(ReportLevel level, String message) {
        logger.log(Level.getLevel(level.name()), message);
        checkLevel(level, message);
    }


    /**
     * Logs a message at the given level including the stack trace of the {@link Throwable} <code>t</code> passed as
     * parameter.
     *
     * @param level the logging level
     * @param message the message to log.
     * @param t the exception to log, including its stack trace.
     */
    public void log(ReportLevel level, String message, Throwable t) {
        logger.log(Level.getLevel(level.name()), message, t);
        checkLevel(level, message);
    }



    /**
     * attach a file to the given level
     *
     * @param level the logging level
     * @param message the message to log.
     * @param file file to attach.
     */
    public void attachFile(ReportLevel level, File file, String message) {
        logger.log(Level.getLevel(level.name()),"attaching a file, file Name: " + file.getName());
        logger.log(Level.getLevel(level.name()), "RP_MESSAGE#FILE#{}#{}", file.getAbsolutePath(), message);
        checkLevel(level, message);
    }


    /**
     * attach a file to the given level including the stack trace of the {@link Throwable} <code>t</code> passed as
     * parameter.
     *
     * @param level the logging level
     * @param message the message to log.
     * @param file file to attach.
     * @param t the exception to log, including its stack trace.
     */
    public void attachFile(ReportLevel level, File file, String message, Throwable t) {
        logger.log(Level.getLevel(level.name()),"attaching a file, file Name: " + file.getName());
        logger.log(Level.getLevel(level.name()), "RP_MESSAGE#FILE#{}#{}", file.getAbsolutePath(), message, t);
        checkLevel(level, message);
    }
}
