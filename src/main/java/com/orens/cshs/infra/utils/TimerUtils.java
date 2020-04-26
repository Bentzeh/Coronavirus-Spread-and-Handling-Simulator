package com.orens.cshs.infra.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimerUtils {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");

    public static long getMillisTimeGapInSeconds(long startTime, long endTime){
        return ((endTime-startTime)/1000);
    }

    public static long getMillisTimeGapInSecondsFromNow(long startTime){
        return ((System.currentTimeMillis()-startTime)/1000);
    }

    public static String getCurrentTimeStampAsFormattedString(){
        return sdf.format(new Timestamp(new Date().getTime()));
    }
    public static long getCurrentTimeStampAsRawLongFromDate(){
        return new Date().getTime();
    }

    public static long getCurrentTimeStampAsRawLongFromSystem(){
        return System.currentTimeMillis();
    }
}
