package com.orens.cshs.infra.logger;


import java.io.File;

public interface IReports {


    /**
     * indicate the beginning of a test
     *
     * @param description description of the test
     */
    public abstract void startLevel(String description);


    /**
     * indicate the end of a test
     *
     */
    public abstract void endLevel();



    /**
     * Logs a message object with the given level.
     *
     * @param level the logging level
     * @param message the message object to log.
     */
    public abstract void log(ReportLevel level, String message);


    /**
     * Logs a message at the given level including the stack trace of the {@link Throwable} <code>t</code> passed as
     * parameter.
     *
     * @param level the logging level
     * @param message the message to log.
     * @param t the exception to log, including its stack trace.
     */
    public abstract  void log(ReportLevel level, String message, Throwable t);



    /**
     * attach a file to the given level
     *
     * @param level the logging level
     * @param message the message to log.
     * @param file file to attach.
     */
    public abstract  void attachFile(ReportLevel level, File file, String message);



    /**
     * attach a file to the given level including the stack trace of the {@link Throwable} <code>t</code> passed as
     * parameter.
     *
     * @param level the logging level
     * @param message the message to log.
     * @param file file to attach.
     * @param t the exception to log, including its stack trace.
     */
    public abstract void attachFile(ReportLevel level, File file, String message, Throwable t);
}
