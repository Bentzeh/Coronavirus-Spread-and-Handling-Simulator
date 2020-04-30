package com.orens.cshs.infra.utils;

import com.orens.cshs.infra.logger.LoggerHandler;
import com.orens.cshs.infra.logger.ReportLevel;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * this class is a util class that responsible for manage all time regarding calculations
 */
public class TimerUtils {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");


    /**
     * this method calculates the time passed between two timestamps in seconds
     * @param startTime the first timestamp (by timeline)
     * @param endTime the second timestamp (by timeline)
     * @return the gap between the given two parameters
     */
    public static long getMillisTimeGapInSeconds(long startTime, long endTime){
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered TimerUtils.getMillisTimeGapInSeconds()");
        return ((endTime-startTime)/1000);
    }

    /**
     * this method calculates the time passed between a timestamps to current time in seconds
     * @param startTime the first timestamp (by timeline)
     * @return the gap between the given timestamp to now
     */
    public static long getMillisTimeGapInSecondsFromNow(long startTime){
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered TimerUtils.getMillisTimeGapInSecondsFromNow()");
        return ((System.currentTimeMillis()-startTime)/1000);
    }

    /**
     * this method calculates the time passed between a timestamps to current time in milliseconds
     * @param startTime the first timestamp (by timeline)
     * @return the gap between the given timestamp to now
     */
    public static long getMillisTimeGapInMillisecondsFromNow(long startTime){
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered TimerUtils.getMillisTimeGapInMillisecondsFromNow()");
        return ((System.currentTimeMillis()-startTime));
    }


    /**
     *  get the current time in a predefined format
     * @return a string that represent the current time
     */
    public static String getCurrentTimeStampAsFormattedString(){
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered TimerUtils.getCurrentTimeStampAsFormattedString()");
        return sdf.format(new Timestamp(new Date().getTime()));
    }

    /**
     * gets the current timestamp from 'Date' object
     * @return the current time
     */
    public static long getCurrentTimeStampAsRawLongFromDate(){
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered TimerUtils.getCurrentTimeStampAsRawLongFromDate()");
        return new Date().getTime();
    }

    /**
     * gets the current timestamp from the System
     * @return the current time
     */
    public static long getCurrentTimeStampAsRawLongFromSystem(){
        LoggerHandler.getInstance().log(ReportLevel.TRACE,"entered TimerUtils.getCurrentTimeStampAsRawLongFromSystem()");
        return System.currentTimeMillis();
    }
}
